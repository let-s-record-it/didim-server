package com.didim.api.schedule.controller

import com.didim.api.schedule.dto.request.ScheduleCategoryAddRequest
import com.didim.api.schedule.dto.request.ScheduleCategoryModifyRequest
import com.didim.api.schedule.dto.response.ScheduleCategoryListResponse
import com.didim.api.support.security.annotation.AuthMember
import com.didim.domain.member.domain.Member
import com.didim.domain.schedule.business.ScheduleCategoryService
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
@RequestMapping("/api/v1/calendars/{calendarId}/categories")
class ScheduleCategoryController(
    private val scheduleCategoryService: ScheduleCategoryService,
) {

    @GetMapping
    fun calendarCategoryList(
        @PathVariable calendarId: Long, @AuthMember member: Member
    ): ResponseEntity<List<ScheduleCategoryListResponse>> {
        return ResponseEntity.ok(
            scheduleCategoryService.findScheduleCategories(calendarId, member.memberKey)
                .map(ScheduleCategoryListResponse::from)
        )
    }

    @PostMapping
    fun addCalendarCategory(
        @PathVariable calendarId: Long,
        @Valid @RequestBody request: ScheduleCategoryAddRequest,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        val id = scheduleCategoryService.addScheduleCategory(request.toNewScheduleCategory(calendarId), member.memberKey)
        return ResponseEntity.created(URI.create("/api/v1/categories/$id")).build()
    }

    @PutMapping("/{categoryId}")
    fun modifyCalendarCategory(
        @PathVariable categoryId: Long,
        @Valid @RequestBody request: ScheduleCategoryModifyRequest,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        scheduleCategoryService.modifyScheduleCategory(request.toEditScheduleCategory(categoryId), member.memberKey)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCalendarCategory(
        @PathVariable categoryId: Long,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        scheduleCategoryService.removeScheduleCategory(categoryId, member.memberKey)
        return ResponseEntity.noContent().build()
    }
}