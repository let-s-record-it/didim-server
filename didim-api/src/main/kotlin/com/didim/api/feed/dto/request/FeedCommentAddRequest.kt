package com.didim.api.feed.dto.request

import jakarta.validation.constraints.Size

data class FeedCommentAddRequest(
    @field:Size(max = 1000)
    val content: String,
)
