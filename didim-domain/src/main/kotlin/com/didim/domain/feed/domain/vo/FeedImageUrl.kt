package com.didim.domain.feed.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

@JvmInline
value class FeedImageUrl(val imageUrl: String) {
    init {
        validate()
    }

    private fun validate() {
        if (imageUrl.isBlank()) {
            throw AppException(ErrorType.BLANK_FEED_IMAGE_URL)
        }
    }
}