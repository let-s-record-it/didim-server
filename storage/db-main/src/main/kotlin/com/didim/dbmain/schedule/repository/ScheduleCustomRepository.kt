package com.didim.dbmain.schedule.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.calendar.entity.QCalendarEntity.calendarEntity
import com.didim.dbmain.schedule.entity.QRepetitionPatternEntity.repetitionPatternEntity
import com.didim.dbmain.schedule.entity.QScheduleAlarmEntity.scheduleAlarmEntity
import com.didim.dbmain.schedule.entity.QScheduleCategoryEntity.scheduleCategoryEntity
import com.didim.dbmain.schedule.entity.QScheduleEntity.scheduleEntity
import com.didim.dbmain.schedule.entity.QScheduleGroupEntity.scheduleGroupEntity
import com.didim.dbmain.schedule.entity.ScheduleEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import com.didim.domain.schedule.domain.NewSchedule
import com.didim.domain.schedule.domain.Schedule
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.Statement
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
@Transactional
internal class ScheduleCustomRepository(
    private val jdbcTemplate: JdbcTemplate,
) : QuerydslRepositorySupport(ScheduleEntity::class) {

    companion object {
        private const val BATCH_SIZE = 1000

        private const val INSERT_QUERY = """
            INSERT INTO SCHEDULE(title, description, is_all_day, start_date_time, end_date_time, place, set_location, latitude, longitude, set_alarm, schedule_category_id, calendar_id, schedule_group_id)
            VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)
        """
    }

    fun saveAll(newSchedules: List<NewSchedule>, scheduleGroupId: Long): List<Long> {
        val generatedIds = mutableListOf<Long>()

        newSchedules.chunked(BATCH_SIZE).forEach { batch ->
            jdbcTemplate.execute { conn: Connection ->
                conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS).use { ps ->
                    batch.onEach {
                        ps.bindSchedule(it, scheduleGroupId)
                        ps.addBatch()
                    }
                    ps.executeBatch()
                    ps.generatedKeys.use { rs ->
                        while (rs.next()) {
                            generatedIds.add(rs.getLong(1))
                        }
                    }
                }
            }
        }

        return generatedIds
    }

    private fun PreparedStatement.bindSchedule(schedule: NewSchedule, scheduleGroupId: Long) {
        var idx = 1
        setString(idx++, schedule.titleValue)
        setString(idx++, schedule.descriptionValue)
        setBoolean(idx++, schedule.isAllDay)
        setObject(idx++, schedule.startDateTime)
        setObject(idx++, schedule.endDateTime)
        setString(idx++, schedule.place)
        setBoolean(idx++, schedule.setLocation)
        setDouble(idx++, schedule.latitude)
        setDouble(idx++, schedule.longitude)
        setBoolean(idx++, schedule.setAlarm)
        setLong(idx++, schedule.categoryId)
        setLong(idx++, schedule.calendarId)
        setLong(idx, scheduleGroupId)
    }

    fun findById(id: Long): ScheduleEntity? =
        selectFrom(scheduleEntity)
            .where(
                scheduleEntity.id.eq(id),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
            ).fetchOne()

    fun findDomainById(id: Long): Schedule? {
        val row = queryFactory
            .select(
                scheduleEntity,
                scheduleGroupEntity,
                repetitionPatternEntity,
                scheduleCategoryEntity,
                calendarEntity,
            )
            .from(scheduleEntity)
            .leftJoin(scheduleGroupEntity)
            .on(scheduleEntity.scheduleGroupId.eq(scheduleGroupEntity.id))
            .leftJoin(repetitionPatternEntity)
            .on(scheduleEntity.scheduleGroupId.eq(repetitionPatternEntity.scheduleGroupId))
            .leftJoin(scheduleCategoryEntity)
            .on(scheduleEntity.scheduleCategoryId.eq(scheduleCategoryEntity.id))
            .leftJoin(calendarEntity)
            .on(scheduleEntity.calendarId.eq(calendarEntity.id))
            .where(
                scheduleEntity.id.eq(id),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetchOne() ?: return null


        val alarmsMap = selectFrom(scheduleAlarmEntity)
            .where(scheduleAlarmEntity.scheduleId.eq(row.get(scheduleEntity)?.id))
            .fetch()
            .groupBy { it.scheduleId }

        val schedule = row.get(scheduleEntity)!!
        val calendar = row.get(calendarEntity)!!
        val group = row.get(scheduleGroupEntity)!!
        val category = row.get(scheduleCategoryEntity)!!
        val pattern = row.get(repetitionPatternEntity)

        return schedule.toDomain(
            alarms = alarmsMap[schedule.id] ?: emptyList(),
            scheduleCategory = category,
            calendar = calendar,
            isRepeated = group.isRepeated,
            pattern = pattern,
        )
    }

    fun findSchedulesInMonth(calendarId: Long, year: Int, month: Int): List<Schedule> {
        val monthStart = LocalDateTime.of(year, month, 1, 0, 0, 0)
        val monthEnd = monthStart.plusMonths(1)

        val results = queryFactory
            .select(
                scheduleEntity,
                scheduleGroupEntity,
                repetitionPatternEntity,
                scheduleCategoryEntity,
                calendarEntity,
            )
            .from(scheduleEntity)
            .leftJoin(scheduleGroupEntity)
            .on(scheduleEntity.scheduleGroupId.eq(scheduleGroupEntity.id))
            .leftJoin(repetitionPatternEntity)
            .on(scheduleEntity.scheduleGroupId.eq(repetitionPatternEntity.scheduleGroupId))
            .leftJoin(scheduleCategoryEntity)
            .on(scheduleEntity.scheduleCategoryId.eq(scheduleCategoryEntity.id))
            .leftJoin(calendarEntity)
            .on(scheduleEntity.calendarId.eq(calendarEntity.id))
            .where(
                scheduleEntity.calendarId.eq(calendarId),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
                scheduleEntity.startDateTime.lt(monthEnd),
                scheduleEntity.endDateTime.goe(monthStart),
            )
            .orderBy(scheduleEntity.startDateTime.asc())
            .fetch()

        if (results.isEmpty()) return emptyList()

        val scheduleIds = results.mapNotNull { it.get(scheduleEntity)?.id }
        val alarmsMap = selectFrom(scheduleAlarmEntity)
            .where(scheduleAlarmEntity.scheduleId.`in`(scheduleIds))
            .fetch()
            .groupBy { it.scheduleId }

        return results.map {
            val schedule = it.get(scheduleEntity)!!
            val calendar = it.get(calendarEntity)!!
            val group = it.get(scheduleGroupEntity)!!
            val category = it.get(scheduleCategoryEntity)!!
            val pattern = it.get(repetitionPatternEntity)

            schedule.toDomain(
                alarms = alarmsMap[schedule.id] ?: emptyList(),
                scheduleCategory = category,
                calendar = calendar,
                isRepeated = group.isRepeated,
                pattern = pattern,
            )
        }
    }

    fun findSchedulesInDay(calendarId: Long, date: LocalDate): List<Schedule> {
        val results = queryFactory
            .select(
                scheduleEntity,
                scheduleGroupEntity,
                repetitionPatternEntity,
                scheduleCategoryEntity,
                calendarEntity,
            )
            .from(scheduleEntity)
            .leftJoin(scheduleGroupEntity)
            .on(scheduleEntity.scheduleGroupId.eq(scheduleGroupEntity.id))
            .leftJoin(repetitionPatternEntity)
            .on(scheduleEntity.scheduleGroupId.eq(repetitionPatternEntity.scheduleGroupId))
            .leftJoin(scheduleCategoryEntity)
            .on(scheduleEntity.scheduleCategoryId.eq(scheduleCategoryEntity.id))
            .leftJoin(calendarEntity)
            .on(scheduleEntity.calendarId.eq(calendarEntity.id))
            .where(
                scheduleEntity.calendarId.eq(calendarId),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
                scheduleEntity.startDateTime.loe(date.atStartOfDay()),
                scheduleEntity.endDateTime.goe(date.atStartOfDay()),
            )
            .orderBy(scheduleEntity.startDateTime.asc())
            .fetch()

        if (results.isEmpty()) return emptyList()

        val scheduleIds = results.mapNotNull { it.get(scheduleEntity)?.id }
        val alarmsMap = selectFrom(scheduleAlarmEntity)
            .where(scheduleAlarmEntity.scheduleId.`in`(scheduleIds))
            .fetch()
            .groupBy { it.scheduleId }

        return results.map {
            val schedule = it.get(scheduleEntity)!!
            val calendar = it.get(calendarEntity)!!
            val group = it.get(scheduleGroupEntity)!!
            val category = it.get(scheduleCategoryEntity)!!
            val pattern = it.get(repetitionPatternEntity)

            schedule.toDomain(
                alarms = alarmsMap[schedule.id] ?: emptyList(),
                scheduleCategory = category,
                calendar = calendar,
                isRepeated = group.isRepeated,
                pattern = pattern,
            )
        }
    }

    fun findSchedulesContainQuery(calendarId: Long, query: String): List<Schedule> {
        val results = queryFactory
            .select(
                scheduleEntity,
                scheduleGroupEntity,
                repetitionPatternEntity,
                scheduleCategoryEntity,
                calendarEntity,
            )
            .from(scheduleEntity)
            .leftJoin(scheduleGroupEntity)
            .on(scheduleEntity.scheduleGroupId.eq(scheduleGroupEntity.id))
            .leftJoin(repetitionPatternEntity)
            .on(scheduleEntity.scheduleGroupId.eq(repetitionPatternEntity.scheduleGroupId))
            .leftJoin(scheduleCategoryEntity)
            .on(scheduleEntity.scheduleCategoryId.eq(scheduleCategoryEntity.id))
            .leftJoin(calendarEntity)
            .on(scheduleEntity.calendarId.eq(calendarEntity.id))
            .where(
                scheduleEntity.calendarId.eq(calendarId),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
                scheduleEntity.title.contains(query),
            )
            .orderBy(scheduleEntity.startDateTime.asc())
            .fetch()

        if (results.isEmpty()) return emptyList()

        val scheduleIds = results.mapNotNull { it.get(scheduleEntity)?.id }
        val alarmsMap = selectFrom(scheduleAlarmEntity)
            .where(scheduleAlarmEntity.scheduleId.`in`(scheduleIds))
            .fetch()
            .groupBy { it.scheduleId }

        return results.map {
            val schedule = it.get(scheduleEntity)!!
            val calendar = it.get(calendarEntity)!!
            val group = it.get(scheduleGroupEntity)!!
            val category = it.get(scheduleCategoryEntity)!!
            val pattern = it.get(repetitionPatternEntity)

            schedule.toDomain(
                alarms = alarmsMap[schedule.id] ?: emptyList(),
                scheduleCategory = category,
                calendar = calendar,
                isRepeated = group.isRepeated,
                pattern = pattern,
            )
        }
    }

    fun findScheduleIdsByScheduleGroupId(scheduleGroupId: Long): List<Long> =
        select(scheduleEntity.id)
            .from(scheduleEntity)
            .where(
                scheduleEntity.scheduleGroupId.eq(scheduleGroupId),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetch()

    fun findScheduleIdsByScheduleGroupIdAfterThis(scheduleGroupId: Long, dateTime: LocalDateTime): List<Long> =
        select(scheduleEntity.id)
            .from(scheduleEntity)
            .where(
                scheduleEntity.scheduleGroupId.eq(scheduleGroupId),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
                scheduleEntity.startDateTime.goe(dateTime),
            )
            .fetch()

    fun findByScheduleGroupId(scheduleGroupId: Long): List<Schedule> {
        val results = queryFactory
            .select(
                scheduleEntity,
                scheduleGroupEntity,
                repetitionPatternEntity,
                scheduleCategoryEntity,
                calendarEntity
            )
            .from(scheduleEntity)
            .leftJoin(scheduleGroupEntity)
            .on(scheduleEntity.scheduleGroupId.eq(scheduleGroupEntity.id))
            .leftJoin(repetitionPatternEntity)
            .on(scheduleEntity.scheduleGroupId.eq(repetitionPatternEntity.scheduleGroupId))
            .leftJoin(scheduleCategoryEntity)
            .on(scheduleEntity.scheduleCategoryId.eq(scheduleCategoryEntity.id))
            .leftJoin(calendarEntity)
            .on(scheduleEntity.calendarId.eq(calendarEntity.id))
            .where(
                scheduleEntity.scheduleGroupId.eq(scheduleGroupId),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetch()

        if (results.isEmpty()) return emptyList()

        val scheduleIds = results.mapNotNull { it.get(scheduleEntity)?.id }
        val alarmsMap = selectFrom(scheduleAlarmEntity)
            .where(scheduleAlarmEntity.scheduleId.`in`(scheduleIds))
            .fetch()
            .groupBy { it.scheduleId }

        return results.map {
            val schedule = it.get(scheduleEntity)!!
            val calendar = it.get(calendarEntity)!!
            val group = it.get(scheduleGroupEntity)!!
            val category = it.get(scheduleCategoryEntity)!!
            val pattern = it.get(repetitionPatternEntity)

            schedule.toDomain(
                alarms = alarmsMap[schedule.id] ?: emptyList(),
                scheduleCategory = category,
                calendar = calendar,
                isRepeated = group.isRepeated,
                pattern = pattern,
            )
        }
    }

    fun existsScheduleByGroupId(scheduleGroupId: Long): Boolean =
        selectOne()
            .from(scheduleEntity)
            .where(
                scheduleEntity.scheduleGroupId.eq(scheduleGroupId),
                scheduleEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetchFirst() != null

    fun updateCategories(categoryId: Long, newCategoryId: Long) {
        flush()

        update(scheduleEntity)
            .set(scheduleEntity.scheduleCategoryId, newCategoryId)
            .where(
                scheduleEntity.scheduleCategoryId.eq(categoryId),
                scheduleEntity.status.eq(EntityStatus.ACTIVE)
            )
            .execute()

        clear()
    }

    fun deleteByScheduleGroupId(scheduleGroupId: Long) {
        flush()

        update(scheduleEntity)
            .set(scheduleEntity.status, EntityStatus.DELETED)
            .where(scheduleEntity.scheduleGroupId.eq(scheduleGroupId))
            .execute()

        clear()
    }

    fun deleteById(id: Long) {
        flush()

        update(scheduleEntity)
            .set(scheduleEntity.status, EntityStatus.DELETED)
            .set(scheduleEntity.deletedAt, LocalDateTime.now())
            .where(scheduleEntity.id.eq(id))
            .execute()

        clear()
    }

    fun deleteByCalendarId(calendarId: Long) {
        flush()

        update(scheduleEntity)
            .set(scheduleEntity.status, EntityStatus.DELETED)
            .set(scheduleEntity.deletedAt, LocalDateTime.now())
            .where(scheduleEntity.calendarId.eq(calendarId))
            .execute()

        clear()
    }
}