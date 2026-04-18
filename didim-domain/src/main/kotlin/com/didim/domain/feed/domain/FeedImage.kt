package com.didim.domain.feed.domain

import com.didim.domain.feed.domain.vo.FeedImageUrl

data class FeedImage(
    val imageUrl: FeedImageUrl,
    val feedId: Long,
    val id: Long,
) {
    companion object {
        fun of(
            imageUrl: String,
            feedId: Long,
            id: Long,
        ) = FeedImage(
            imageUrl = FeedImageUrl(imageUrl),
            feedId = feedId,
            id = id,
        )
    }

    val imageUrlValue: String
        get() = imageUrl.imageUrl
}
