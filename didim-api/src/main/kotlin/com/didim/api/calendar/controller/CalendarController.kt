package com.didim.api.calendar.controller

import com.didim.api.calendar.dto.request.CalendarAddRequest
import com.didim.api.support.security.annotation.AuthMember
import com.didim.domain.calendar.business.CalendarService
import com.didim.domain.calendar.domain.Calendar
import com.didim.domain.calendar.domain.CalendarMember
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
    private val calendarService: CalendarService
) {

    @GetMapping
    fun calendarList(@AuthMember member: Member): ResponseEntity<List<CalendarResponse>> {
        return ResponseEntity.ok<T?>(calendarMemberService.searchCalendarsByMemberId(member.getId()))
    }

    @PostMapping
    fun addCalendar(
        @Valid @RequestBody request: CalendarAddRequest, @AuthMember member: Member
    ): ResponseEntity<CalendarResponse> {
        val calendar: Calendar = calendarCommandService.addCalendar(request, member.getId())
        return ResponseEntity.created(URI.create("/api/v1/calendars/" + calendar.getId()))
            .body<T?>(CalendarResponse.from(calendar))
    }

    @PutMapping("/{calendarId}")
    fun calendarModify(
        @Valid @RequestBody request: CalendarModifyRequest?,
        @PathVariable calendarId: Long?,
        @AuthMember member: Member
    ): ResponseEntity<Void?> {
        calendarCommandService.modifyCalendar(request, calendarId, member.getId())
        return ResponseEntity.noContent().build<Void?>()
    }

    @DeleteMapping("/{calendarId}")
    fun calendarDelete(
        @PathVariable calendarId: Long?, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        calendarCommandService.removeByCalendarId(calendarId, member.getId())
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{calendarId}/members")
    fun calendarMemberList(
        @PathVariable calendarId: Long?
    ): ResponseEntity<List<CalendarMemberResponse>> {
        return ResponseEntity.ok<T?>(calendarMemberService.searchCalendarMembers(calendarId))
    }

    @GetMapping("/{calendarId}/members/{memberId}")
    fun calendarMemberDetails(
        @PathVariable calendarId: Long?, @PathVariable memberId: Long?
    ): ResponseEntity<CalendarMemberResponse?> {
        val calendarMember: CalendarMember =
            calendarMemberService.searchCalendarMember(calendarId, memberId)
        val member: Member? = memberQueryService.findByMemberId(memberId)
        return ResponseEntity.ok(CalendarMemberResponse.of(calendarMember, member))
    }

    @DeleteMapping("/{calendarId}/members/{memberId}")
    fun calendarMemberDelete(
        @PathVariable calendarId: Long?,
        @PathVariable memberId: Long?,
        @CurrentMember member: Member
    ): ResponseEntity<Unit> {
        calendarMemberService.removeCalendarMember(calendarId, memberId, member.getId())
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/join")
    fun joinInCalendar(
        @RequestBody request: JoinInCalendarRequest, @CurrentMember member: Member
    ): ResponseEntity<Unit> {
        joinCalendarService.joinInCalendar(request.inviteCode(), member.getId())
        return ResponseEntity.created(URI.create("")).build()
    }
}