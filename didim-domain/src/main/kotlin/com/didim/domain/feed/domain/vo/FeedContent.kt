package com.didim.domain.feed.domain.vo

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

@JvmInline
value class FeedContent(
    val content: String,
) {
    companion object {
        private const val MAX_CONTENT_LENGTH = 5000
    }

    init {
        validateContentLength()
    }

    private fun validateContentLength() {
        if (content.length > MAX_CONTENT_LENGTH) {
            throw AppException(ErrorType.INVALID_FEED_CONTENT_LENGTH)
        }
    }
}
