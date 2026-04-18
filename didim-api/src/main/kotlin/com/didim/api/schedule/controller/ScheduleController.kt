package com.didim.api.schedule.controller

import com.didim.api.schedule.dto.request.ScheduleAddRequest
import com.didim.api.schedule.dto.request.ScheduleModifyRequest
import com.didim.api.schedule.dto.response.DayScheduleResponse
import com.didim.api.schedule.dto.response.MonthScheduleResponse
import com.didim.api.support.security.annotation.AuthMember
import com.didim.domain.member.domain.Member
import com.didim.domain.schedule.business.ScheduleService
import jakarta.validation.constraints.Size
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/calendars/{calendarId}/schedules")
class ScheduleController(
    private val scheduleService: ScheduleService,
) {

    @PostMapping
    fun schedulesAdd(
        @Validated @RequestBody request: ScheduleAddRequest,
        @PathVariable calendarId: Long,
        @AuthMember member: Member
    ): ResponseEntity<List<MonthScheduleResponse>> {
        return ResponseEntity.ok(
            scheduleService.addSchedules(request.toNewSchedule(calendarId, member.memberKey))
                .map(MonthScheduleResponse::from)
        )
    }

    @GetMapping("/{scheduleId}")
    fun scheduleDetails(
        @PathVariable scheduleId: Long, @AuthMember member: Member
    ): ResponseEntity<DayScheduleResponse> {
        return ResponseEntity.ok(DayScheduleResponse.from(scheduleService.findSchedule(scheduleId, member.memberKey)))
    }

    @GetMapping("/month")
    fun schedulesInMonth(
        @PathVariable calendarId: Long,
        @RequestParam year: Int,
        @RequestParam month: Int,
        @AuthMember member: Member
    ): ResponseEntity<List<MonthScheduleResponse>> {
        return ResponseEntity.ok(
            scheduleService.findSchedulesInMonth(calendarId, year, month, member.memberKey)
                .map(MonthScheduleResponse::from)
        )
    }

    @GetMapping("/day")
    fun schedulesInDay(
        @PathVariable calendarId: Long,
        @RequestParam date: LocalDate,
        @AuthMember member: Member
    ): ResponseEntity<List<DayScheduleResponse>> {
        return ResponseEntity.ok(
            scheduleService.findSchedulesInDay(calendarId, date, member.memberKey)
                .map(DayScheduleResponse::from)
        )
    }

    @GetMapping("/search")
    fun searchSchedules(
        @RequestParam query: @Size(min = 1) String,
        @PathVariable calendarId: Long,
        @AuthMember member: Member
    ): ResponseEntity<List<DayScheduleResponse>> {
        return ResponseEntity.ok(
            scheduleService.findSchedulesContainQuery(calendarId, query, member.memberKey)
                .map(DayScheduleResponse::from)
        )
    }

    @PutMapping("/{scheduleId}")
    fun modifySchedule(
        @PathVariable scheduleId: Long,
        @RequestBody request: ScheduleModifyRequest,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        scheduleService.modifySchedule(request.toEditSchedule(scheduleId, member.memberKey))
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{scheduleId}/group")
    fun modifySchedules(
        @PathVariable scheduleId: Long,
        @RequestBody request: ScheduleModifyRequest,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        scheduleService.modifySchedulesInGroup(request.toEditSchedule(scheduleId, member.memberKey))
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{scheduleId}")
    fun removeScheduleById(
        @PathVariable scheduleId: Long, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        scheduleService.removeSchedule(scheduleId, member.memberKey)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{scheduleId}/group")
    fun removeSchedulesInGroup(
        @PathVariable scheduleId: Long, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        scheduleService.removeSchedulesInGroup(scheduleId, member.memberKey)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{scheduleId}/after")
    fun removeSchedulesInGroupAfter(
        @PathVariable scheduleId: Long, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        scheduleService.removeSchedulesInGroupAfterThis(scheduleId, member.memberKey)
        return ResponseEntity.noContent().build()
    }
}