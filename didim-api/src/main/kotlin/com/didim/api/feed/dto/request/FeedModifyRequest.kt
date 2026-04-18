package com.didim.api.feed.dto.request

import com.didim.domain.feed.domain.EditFeed
import jakarta.validation.constraints.Size

data class FeedModifyRequest(
    @field:Size(min = 1, max = 30)
    val title: String,
    @field:Size(max = 5000)
    val content: String,
    val removeImageIds: List<Long>,
) {
    fun toEditFeed(id: Long, memberKey: String) = EditFeed.of(
        id = id,
        title = title,
        content = content,
        removeImageIds = removeImageIds,
        memberKey = memberKey,
    )
}
