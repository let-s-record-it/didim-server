package com.didim.domain.feed.domain

import com.didim.domain.feed.domain.vo.FeedContent
import com.didim.domain.feed.domain.vo.FeedTitle

data class EditFeed(
    val id: Long,
    val title: FeedTitle,
    val content: FeedContent,
    val removeImageIds: List<Long>,
    val memberKey: String,
) {
    companion object {
        fun of(
            id: Long,
            title: String,
            content: String,
            removeImageIds: List<Long>,
            memberKey: String,
        ) = EditFeed(
            id = id,
            title = FeedTitle(title),
            content = FeedContent(content),
            removeImageIds = removeImageIds,
            memberKey = memberKey,
        )
    }

    val titleValue: String get() = title.title
    val contentValue: String get() = content.content
}
