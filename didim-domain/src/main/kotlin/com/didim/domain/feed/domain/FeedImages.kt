package com.didim.domain.feed.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class FeedImages(
    val feedImages: List<FeedImage>,
) {
    val feedImageCount: Int = feedImages.size

    companion object {
        private const val MAX_FEED_IMAGE_COUNT = 10
    }

    init {
        validateImageCount()
    }

    private fun validateImageCount() {
        if (feedImages.size > MAX_FEED_IMAGE_COUNT) {
            throw AppException(ErrorType.OVER_FEED_IMAGE_COUNT)
        }
    }
}
