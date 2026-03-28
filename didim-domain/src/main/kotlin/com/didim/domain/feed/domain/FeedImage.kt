package com.didim.domain.feed.domain

data class FeedImage(
    val imageUrl: FeedImageUrl,
    val feedId: Long,
    val id: Long? = null,
) {
    constructor(imageUrl: String, feedId: Long) : this(FeedImageUrl(imageUrl), feedId)

    val imageUrlValue: String
        get() = imageUrl.imageUrl
}
