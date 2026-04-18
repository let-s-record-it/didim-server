package com.didim.api.calendar.controller

import com.didim.api.calendar.dto.request.CalendarAddRequest
import com.didim.api.calendar.dto.request.CalendarModifyRequest
import com.didim.api.calendar.dto.request.JoinInCalendarRequest
import com.didim.api.calendar.dto.response.CalendarMemberResponse
import com.didim.api.calendar.dto.response.CalendarResponse
import com.didim.api.support.security.annotation.AuthMember
import com.didim.domain.calendar.business.CalendarMemberService
import com.didim.domain.calendar.business.CalendarService
import com.didim.domain.member.domain.Member
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/calendars")
class CalendarController(
    private val calendarService: CalendarService,
    private val calendarMemberService: CalendarMemberService,
) {

    @GetMapping
    fun calendarList(@AuthMember member: Member): ResponseEntity<List<CalendarResponse>> {
        return ResponseEntity.ok(calendarService.findCalendars(member.memberKey).map(CalendarResponse::from))
    }

    @PostMapping
    fun addCalendar(
        @Valid @RequestBody request: CalendarAddRequest, @AuthMember member: Member
    ): ResponseEntity<CalendarResponse> {
        val calendarId = calendarService.createCalendar(request.toNewCalendar(member.memberKey))
        return ResponseEntity.created(URI.create("/api/v1/calendars/$calendarId")).build()
    }

    @PutMapping("/{calendarId}")
    fun calendarModify(
        @Valid @RequestBody request: CalendarModifyRequest,
        @PathVariable calendarId: Long,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        calendarService.modifyCalendar(request.toEditCalendar(calendarId, member.memberKey))
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{calendarId}")
    fun calendarDelete(
        @PathVariable calendarId: Long, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        calendarService.removeCalendar(calendarId, member.memberKey)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{calendarId}/members")
    fun calendarMemberList(
        @PathVariable calendarId: Long,
        @AuthMember member: Member,
    ): ResponseEntity<List<CalendarMemberResponse>> {
        return ResponseEntity.ok(
            calendarMemberService.findCalendarMembers(calendarId, member.memberKey).map(CalendarMemberResponse::from)
        )
    }

    @GetMapping("/{calendarId}/members/{memberKey}")
    fun calendarMemberDetails(
        @PathVariable calendarId: Long,
        @PathVariable memberKey: String,
        @AuthMember member: Member,
    ): ResponseEntity<CalendarMemberResponse> {
        val calendarMember = calendarMemberService.findCalendarMember(calendarId, memberKey, member.memberKey)
        return ResponseEntity.ok(CalendarMemberResponse.from(calendarMember))
    }

    @DeleteMapping("/{calendarId}/members/{memberKey}")
    fun calendarMemberDelete(
        @PathVariable calendarId: Long,
        @PathVariable memberKey: String,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        calendarMemberService.removeCalendarMember(calendarId, memberKey, member.memberKey)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/join")
    fun joinInCalendar(@RequestBody request: JoinInCalendarRequest, @AuthMember member: Member): ResponseEntity<Unit> {
        calendarService.joinInCalendar(request.inviteCode, member.memberKey)
        return ResponseEntity.created(URI.create("")).build()
    }
}