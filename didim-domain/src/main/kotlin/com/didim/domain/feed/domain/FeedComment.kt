package com.didim.domain.feed.domain

data class FeedComment(
    val content: FeedCommentContent,
    val feedId: Long,
    val memberId: Long,
    val id: Long? = null,
) {
    constructor(content: String, feedId: Long, memberId: Long)
            : this(FeedCommentContent(content), feedId, memberId)

    val contentValue: String
        get() = content.content
}
