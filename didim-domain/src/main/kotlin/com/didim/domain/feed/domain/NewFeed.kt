package com.didim.domain.feed.domain

import com.didim.domain.feed.domain.vo.FeedContent
import com.didim.domain.feed.domain.vo.FeedTitle

data class NewFeed(
    val title: FeedTitle,
    val content: FeedContent,
    val memberKey: String,
) {
    companion object {
        fun of(
            title: String,
            content: String,
            memberKey: String,
        ) = NewFeed(
            title = FeedTitle(title),
            content = FeedContent(content),
            memberKey = memberKey,
        )
    }

    val titleValue: String get() = title.title
    val contentValue: String get() = content.content
}