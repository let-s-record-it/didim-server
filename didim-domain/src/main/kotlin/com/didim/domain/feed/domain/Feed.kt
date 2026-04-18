package com.didim.domain.feed.domain

import com.didim.domain.feed.domain.vo.FeedContent
import com.didim.domain.feed.domain.vo.FeedTitle
import java.time.LocalDateTime

data class Feed(
    val id: Long,
    val title: FeedTitle,
    val content: FeedContent,
    val createdAt: LocalDateTime,
    val feedImages: FeedImages,
    val memberKey: String,
    val likeCount: Long,
) {
    companion object {
        fun of(
            id: Long,
            title: String,
            content: String,
            createdAt: LocalDateTime,
            feedImages: FeedImages,
            memberKey: String,
            likeCount: Long,
        ) = Feed(
            id = id,
            title = FeedTitle(title),
            content = FeedContent(content),
            createdAt = createdAt,
            feedImages = feedImages,
            memberKey = memberKey,
            likeCount = likeCount,
        )
    }

    fun isOwner(memberKey: String) = this.memberKey == memberKey

    val titleValue: String
        get() = title.title

    val contentValue: String
        get() = content.content

    val feedImageUrls: List<String>
        get() = feedImages.feedImages.map { it.imageUrl.imageUrl }
}