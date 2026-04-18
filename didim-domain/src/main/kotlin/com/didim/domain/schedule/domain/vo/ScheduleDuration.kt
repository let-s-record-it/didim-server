package com.didim.domain.schedule.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class ScheduleDuration(
    val isAllDay: Boolean,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
) {
    companion object {
        fun of(
            isAllDay: Boolean,
            startDateTime: LocalDateTime,
            endDateTime: LocalDateTime,
        ) = if (isAllDay) {
            ScheduleDuration(
                true,
                startDateTime.truncatedTo(ChronoUnit.DAYS),
                endDateTime.truncatedTo(ChronoUnit.DAYS),
            )
        } else {
            ScheduleDuration(
                false,
                startDateTime.truncatedTo(ChronoUnit.MINUTES),
                endDateTime.truncatedTo(ChronoUnit.MINUTES),
            )
        }
    }

    init {
        if (startDateTime.isAfter(endDateTime)) {
            throw AppException(ErrorType.INVALID_DURATION)
        }

        if (isAllDay) {
            startDateTime.withSecond(0).withNano(0)
        }
    }
}
