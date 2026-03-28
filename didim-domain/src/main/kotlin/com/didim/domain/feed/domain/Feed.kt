package com.didim.domain.feed.domain

data class Feed(
    val title: FeedTitle,
    val content: FeedContent,
    val memberId: Long,
    val likeCount: Long = 0,
    val id: Long? = null,
) {

    constructor(title: String, content: String, memberId: Long)
            : this(FeedTitle(title), FeedContent(content), memberId)

    val titleValue: String
        get() = title.title

    val contentValue: String
        get() = content.content
}
