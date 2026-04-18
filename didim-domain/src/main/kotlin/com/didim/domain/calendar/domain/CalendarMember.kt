package com.didim.domain.calendar.domain

data class CalendarMember(
    val calendarId: Long,
    val memberKey: String,
    val memberName: String,
    val memberProfileImage: String,
    val id: Long? = null,
) {
}