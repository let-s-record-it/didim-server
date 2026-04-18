package com.didim.domain.schedule.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import java.time.LocalDateTime

data class RepetitionDuration(
    val repetitionStartDate: LocalDateTime,
    val repetitionEndDate: LocalDateTime,
) {
    init {
        if (repetitionStartDate.isAfter(repetitionEndDate)) {
            throw AppException(ErrorType.INVALID_DURATION)
        }
    }
}
