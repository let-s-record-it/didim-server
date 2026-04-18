package com.didim.dbmain.calendar.repository

import com.didim.dbmain.calendar.entity.CalendarMemberEntity
import com.didim.domain.calendar.dataaccess.CalendarMemberRepository
import com.didim.domain.calendar.domain.CalendarMember
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class CalendarMemberEntityRepository(
    private val calendarMemberJpaRepository: CalendarMemberJpaRepository,
    private val calendarMemberCustomRepository: CalendarMemberCustomRepository,
) : CalendarMemberRepository {

    override fun save(calendarId: Long, memberKey: String): Long =
        calendarMemberJpaRepository.save(CalendarMemberEntity(calendarId, memberKey)).id!!

    override fun existsByCalendarIdAndMemberKey(calendarId: Long, memberKey: String): Boolean =
        calendarMemberCustomRepository.existsByCalendarIdAndMemberKey(calendarId, memberKey)

    override fun findByCalendarId(calendarId: Long): List<CalendarMember> =
        calendarMemberCustomRepository.findByCalendarId(calendarId)

    override fun findByCalendarIdAndMemberKey(calendarId: Long, memberKey: String): CalendarMember? =
        calendarMemberCustomRepository.findByCalendarIdAndMemberKey(calendarId, memberKey)

    override fun deleteByCalendarId(calendarId: Long) {
        calendarMemberCustomRepository.deleteByCalendarId(calendarId)
    }

    override fun deleteByCalendarIdAndMemberKey(calendarId: Long, memberKey: String) {
        calendarMemberCustomRepository.deleteByCalendarIdAndMemberKey(calendarId, memberKey)
    }
}