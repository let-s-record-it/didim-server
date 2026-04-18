package com.didim.api.feed.dto.request

import com.didim.domain.feed.domain.NewFeed
import jakarta.validation.constraints.Size

data class FeedAddRequest(
    @field:Size(min = 1, max = 30)
    val title: String,
    @field:Size(max = 5000)
    val content: String,
) {
    fun toNewFeed(memberKey: String) = NewFeed.of(
        title = title,
        content = content,
        memberKey = memberKey,
    )
}
