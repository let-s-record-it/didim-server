package com.didim.api.feed.dto.response

import java.time.LocalDateTime

data class FeedCommentInListResponse(
    val id: Long,
    val content: String,
    val createdAt: LocalDateTime,
    val memberKey: String,
    val memberName: String,
    val memberJob: String,
    val memberProfileImageUrl: String,
    val isOwner: Boolean,
)
