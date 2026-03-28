package com.didim.dbmain.calendar.repository

import com.didim.dbmain.calendar.entity.CalendarEntity
import com.didim.domain.calendar.dataaccess.CalendarRepository
import com.didim.domain.calendar.domain.Calendar
import com.didim.domain.calendar.domain.EditCalendar
import com.didim.domain.calendar.domain.NewCalendar
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class CalendarEntityRepository(
    private val calendarJpaRepository: CalendarJpaRepository,
    private val calendarCustomRepository: CalendarCustomRepository,
) : CalendarRepository {

    override fun save(newCalendar: NewCalendar): Long =
        calendarJpaRepository.save(CalendarEntity.from(newCalendar)).id!!

    override fun findById(id: Long): Calendar? {
        return calendarCustomRepository.findById(id)?.toDomain()
    }

    override fun update(editCalendar: EditCalendar) {
        calendarCustomRepository.findById(editCalendar.id)?.update(editCalendar.titleValue, editCalendar.categoryId)
    }
}