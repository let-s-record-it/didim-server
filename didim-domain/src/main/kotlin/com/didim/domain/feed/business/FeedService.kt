package com.didim.domain.feed.business

import com.didim.common.domain.ImageData
import com.didim.common.pagination.Pageable
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.common.pagination.Page
import com.didim.domain.feed.domain.EditFeed
import com.didim.domain.feed.domain.Feed
import com.didim.domain.feed.domain.NewFeed
import com.didim.domain.feed.implement.FeedLikeManager
import com.didim.domain.feed.implement.FeedManager
import com.didim.domain.feed.implement.FeedScrapManager
import org.springframework.stereotype.Service

@Service
class FeedService(
    private val feedManager: FeedManager,
    private val feedLikeManager: FeedLikeManager,
    private val feedScrapManager: FeedScrapManager,
) {

    fun addFeed(newFeed: NewFeed, newFeedImages: List<ImageData>) = feedManager.add(newFeed, newFeedImages)

    fun findFeed(id: Long) = feedManager.find(id)

    fun findLatestFeeds(pageable: Pageable, memberKey: String): Page<Feed> {

    }

    fun modifyFeed(editFeed: EditFeed, newFeedImages: List<ImageData>) {
        if (feedManager.find(editFeed.id).isOwner(editFeed.memberKey)) {
            throw AppException(ErrorType.INVALID_FEED_MODIFY_REQUEST)
        }

        feedManager.modify(editFeed, newFeedImages)
    }

    fun removeFeed(id: Long) = feedManager.remove(id)

    fun feedLike(feedId: Long, memberKey: String) = feedLikeManager.like(feedId, memberKey)

    fun feedUnlike(feedId: Long, memberKey: String) = feedLikeManager.unlike(feedId, memberKey)

    fun isLiked(feedId: Long, memberKey: String) = feedLikeManager.isLiked(feedId, memberKey)

    fun feedScrap(feedId: Long, memberKey: String) = feedScrapManager.scrap(feedId, memberKey)

    fun feedUnScrap(feedId: Long, memberKey: String) = feedScrapManager.unScrap(feedId, memberKey)

    fun isScraped(feedId: Long, memberKey: String) = feedScrapManager.isScraped(feedId, memberKey)
}