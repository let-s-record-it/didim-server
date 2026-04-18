package com.didim.dbmain.calendar.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.calendar.entity.CalendarMemberEntity
import com.didim.dbmain.calendar.entity.QCalendarMemberEntity.calendarMemberEntity
import com.didim.dbmain.member.entity.QMemberEntity
import com.didim.dbmain.member.entity.QMemberEntity.memberEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import com.didim.domain.calendar.domain.CalendarMember
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class CalendarMemberCustomRepository : QuerydslRepositorySupport(CalendarMemberEntity::class) {

    fun existsByCalendarIdAndMemberKey(calendarId: Long, memberKey: String): Boolean =
        selectOne()
            .from(calendarMemberEntity)
            .where(
                calendarMemberEntity.calendarId.eq(calendarId),
                calendarMemberEntity.memberKey.eq(memberKey),
                calendarMemberEntity.status.eq(EntityStatus.ACTIVE)
            )
            .fetchFirst() != null

    fun findByCalendarId(calendarId: Long): List<CalendarMember> =
        queryFactory.select(
            calendarMemberEntity.id,
            calendarMemberEntity.calendarId,
            calendarMemberEntity.memberKey,
            memberEntity.name,
            memberEntity.profileImageUrl,
        ).from(calendarMemberEntity)
            .innerJoin(memberEntity)
            .on(calendarMemberEntity.memberKey.eq(memberEntity.memberKey))
            .where(
                calendarMemberEntity.calendarId.eq(calendarId),
                calendarMemberEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetch()
            .map {
                CalendarMember(
                    id = it.get(calendarMemberEntity.id),
                    calendarId = it.get(calendarMemberEntity.calendarId)!!,
                    memberKey = it.get(calendarMemberEntity.memberKey)!!,
                    memberName = it.get(memberEntity.name)!!,
                    memberProfileImage = it.get(memberEntity.profileImageUrl)!!,
                )
            }

    fun findByCalendarIdAndMemberKey(calendarId: Long, memberKey: String): CalendarMember? {
        val row = queryFactory.select(
            calendarMemberEntity.id,
            calendarMemberEntity.calendarId,
            calendarMemberEntity.memberKey,
            memberEntity.name,
            memberEntity.profileImageUrl,
        ).from(calendarMemberEntity)
            .innerJoin(memberEntity)
            .on(calendarMemberEntity.memberKey.eq(memberEntity.memberKey))
            .where(
                calendarMemberEntity.calendarId.eq(calendarId),
                calendarMemberEntity.memberKey.eq(memberKey),
                calendarMemberEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetchOne() ?: return null

        return CalendarMember(
            id = row.get(calendarMemberEntity.id),
            calendarId = row.get(calendarMemberEntity.calendarId)!!,
            memberKey = row.get(calendarMemberEntity.memberKey)!!,
            memberName = row.get(memberEntity.name)!!,
            memberProfileImage = row.get(memberEntity.profileImageUrl)!!,
        )
    }

    fun deleteByCalendarId(calendarId: Long) {
        flush()

        update(calendarMemberEntity)
            .set(calendarMemberEntity.status, EntityStatus.DELETED)
            .where(
                calendarMemberEntity.calendarId.eq(calendarId),
                calendarMemberEntity.status.eq(EntityStatus.ACTIVE)
            ).execute()

        clear()
    }

    fun deleteByCalendarIdAndMemberKey(calendarId: Long, memberKey: String) {
        flush()

        update(calendarMemberEntity)
            .set(calendarMemberEntity.status, EntityStatus.DELETED)
            .where(
                calendarMemberEntity.calendarId.eq(calendarId),
                calendarMemberEntity.memberKey.eq(memberKey),
                calendarMemberEntity.status.eq(EntityStatus.ACTIVE)
            ).execute()

        clear()
    }
}