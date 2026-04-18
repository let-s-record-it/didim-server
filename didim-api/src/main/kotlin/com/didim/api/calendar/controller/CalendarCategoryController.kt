package com.didim.api.calendar.controller

import com.didim.api.calendar.dto.request.CalendarCategoryCreateRequest
import com.didim.api.calendar.dto.request.CalendarCategoryModifyRequest
import com.didim.api.calendar.dto.response.CalendarCategoryListResponse
import com.didim.api.support.security.annotation.AuthMember
import com.didim.domain.calendar.business.CalendarCategoryService
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
@RequestMapping("/api/v1/calendar-categories")
class CalendarCategoryController(
    private val calendarCategoryService: CalendarCategoryService,
) {

    @GetMapping
    fun calendarCategoryList(@AuthMember member: Member): ResponseEntity<List<CalendarCategoryListResponse>> {
        return ResponseEntity.ok(
            calendarCategoryService.findCalendarCategories(member.memberKey).map(CalendarCategoryListResponse::from)
        )
    }

    @PostMapping
    fun addCalendarCategory(
        @Valid @RequestBody request: CalendarCategoryCreateRequest, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        val id = calendarCategoryService.addCalendarCategory(request.toNewCalendarCategory(member.memberKey))
        return ResponseEntity.created(URI.create("/api/v1/calendar-categories/$id")).build()
    }

    @PutMapping("/{categoryId}")
    fun modifyCalendarCategory(
        @PathVariable categoryId: Long,
        @Valid @RequestBody request: CalendarCategoryModifyRequest,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        calendarCategoryService.modifyCalendarCategory(request.toEditCalendarCategory(categoryId, member.memberKey))
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCalendarCategory(
        @PathVariable categoryId: Long, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        calendarCategoryService.removeCalendarCategory(categoryId, member.memberKey)
        return ResponseEntity.noContent().build()
    }
}