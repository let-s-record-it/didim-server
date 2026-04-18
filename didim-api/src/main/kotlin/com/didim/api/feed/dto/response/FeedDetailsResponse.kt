package com.didim.api.feed.dto.response

import com.didim.domain.feed.domain.Feed
import com.didim.domain.member.domain.Member
import java.time.LocalDateTime

data class FeedDetailsResponse(
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
    val isOwner: Boolean,
) {
    companion object {
        fun of(feed: Feed, isLiked: Boolean, isScraped: Boolean, member: Member) = FeedDetailsResponse(
            id = feed.id,
            title = feed.titleValue,
            content = feed.contentValue,
            createdAt = feed.createdAt,
            feedImageUrls = feed.feedImageUrls,
            likeCount = feed.likeCount,
            isLiked = isLiked,
            isScraped = isScraped,
            memberName = member.name,
            memberJob = member.job,
            memberProfileImageUrl = member.profileImageUrl,
            isOwner = feed.isOwner(member.memberKey),
        )
    }
}