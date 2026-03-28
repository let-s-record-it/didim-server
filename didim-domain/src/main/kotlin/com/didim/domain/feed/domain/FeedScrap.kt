package com.didim.domain.feed.domain

data class FeedScrap(
    val feedId: Long,
    val memberId: Long,
    val id: Long? = null,
)