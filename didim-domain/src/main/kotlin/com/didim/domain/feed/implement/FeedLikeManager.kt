package com.didim.domain.feed.implement

import com.didim.domain.feed.dataaccess.FeedLikeRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class FeedLikeManager(
    private val feedLikeRepository: FeedLikeRepository,
) {

    fun like(feedId: Long, memberKey: String) = feedLikeRepository.save(feedId, memberKey)

    fun unlike(feedId: Long, memberKey: String) = feedLikeRepository.delete(feedId, memberKey)

    fun isLiked(feedId: Long, memberKey: String) = feedLikeRepository.existsByFeedIdAndMemberKey(feedId, memberKey)
}