package com.didim.domain.schedule.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class Location(
    val latitude: Double,
    val longitude: Double,
) {
    companion object {
        private const val MIN_LATITUDE = 0.0
        private const val MIN_LONGITUDE = 0.0
        private const val MAX_LATITUDE = 90.0
        private const val MAX_LONGITUDE = 180.0
    }

    init {
        validateLatitudeRange()
        validateLongitudeRange()
    }

    private fun validateLatitudeRange() {
        if (latitude !in MIN_LATITUDE..MAX_LATITUDE) {
            throw AppException(ErrorType.LATITUDE_OUT_OF_RANGE)
        }
    }

    private fun validateLongitudeRange() {
        if (longitude !in MIN_LONGITUDE..MAX_LONGITUDE) {
            throw AppException(ErrorType.LONGITUDE_OUT_OF_RANGE)
        }
    }
}
