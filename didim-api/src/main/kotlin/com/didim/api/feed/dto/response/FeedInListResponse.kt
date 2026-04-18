package com.didim.api.feed.dto.response

import java.time.LocalDateTime

data class FeedInListResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val feedImageUrls: List<String>,
    val likeCount: Long,
    val isLiked: Boolean,
    val isScraped: Boolean,
    val memberName: String,
    val memberJob: String,
    val memberProfileImageUrl: String,
)
