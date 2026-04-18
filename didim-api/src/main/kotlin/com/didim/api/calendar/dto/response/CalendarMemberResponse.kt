package com.didim.api.calendar.dto.response

import com.didim.domain.calendar.domain.CalendarMember

data class CalendarMemberResponse(
    val id: Long,
    val memberKey: String,
    val memberName: String,
    val memberProfileImageUrl: String,
) {
    companion object {
        fun from(calendarMember: CalendarMember) = CalendarMemberResponse(
            id = calendarMember.id!!,
            memberKey = calendarMember.memberKey,
            memberName = calendarMember.memberName,
            memberProfileImageUrl = calendarMember.memberProfileImage,
        )
    }
}
