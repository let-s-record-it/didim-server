package com.didim.domain.schedule.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

@JvmInline
value class ScheduleTitle(
    val title: String,
) {
    companion object {
        private const val MAX_TITLE_LENGTH = 30
    }

    init {
        if (title.isBlank()) {
            throw AppException(ErrorType.BLANK_SCHEDULE_TITLE)
        }

        if (title.length > MAX_TITLE_LENGTH) {
            throw AppException(ErrorType.INVALID_SCHEDULE_TITLE_LENGTH)
        }
    }
}
