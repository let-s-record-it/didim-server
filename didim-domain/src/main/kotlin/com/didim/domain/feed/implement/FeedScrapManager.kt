package com.didim.domain.feed.implement

import com.didim.domain.feed.dataaccess.FeedScrapRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class FeedScrapManager(
    private val feedScrapRepository: FeedScrapRepository,
) {

    fun scrap(feedId: Long, memberKey: String) = feedScrapRepository.save(feedId, memberKey)

    fun unScrap(feedId: Long, memberKey: String) = feedScrapRepository.delete(feedId, memberKey)

    fun isScraped(feedId: Long, memberKey: String) = feedScrapRepository.existsByFeedIdAndMemberKey(feedId, memberKey)
}