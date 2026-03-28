package com.didim.domain.feed.domain

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType

data class FeedCommentContent(
    val content: String,
) {
    companion object {
        private const val MAX_CONTENT_LENGTH = 1000
    }

    init {
        validateContentLength()
    }

    private fun validateContentLength() {
        if (content.length > MAX_CONTENT_LENGTH) {
            throw AppException(ErrorType.INVALID_FEED_COMMENT_CONTENT_LENGTH)
        }
    }
}
