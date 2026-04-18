package com.didim.domain.feed.implement

import com.didim.common.domain.ImageData
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.common.pagination.Page
import com.didim.common.pagination.Pageable
import com.didim.domain.feed.dataaccess.FeedImageRepository
import com.didim.domain.feed.dataaccess.FeedRepository
import com.didim.domain.feed.domain.EditFeed
import com.didim.domain.feed.domain.Feed
import com.didim.domain.feed.domain.NewFeed
import com.didim.domain.support.ImageUploader
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class FeedManager(
    private val feedRepository: FeedRepository,
    private val feedImageRepository: FeedImageRepository,
    private val imageUploader: ImageUploader,
) {
    fun add(newFeed: NewFeed, newFeedImages: List<ImageData>) = feedRepository.save(newFeed).also {
        val imageUrls = newFeedImages.map(imageUploader::upload)
        feedImageRepository.saveAll(imageUrls, it)
    }

    @Transactional(readOnly = true)
    fun find(id: Long) = feedRepository.findById(id) ?: throw AppException(ErrorType.FEED_NOT_FOUND)

    @Transactional(readOnly = true)
    fun findLatestFeeds(pageable: Pageable, memberKey: String): Page<Feed> {

    }

    fun modify(editFeed: EditFeed, newFeedImages: List<ImageData>) {
        feedImageRepository.delete(editFeed.removeImageIds)
        val imageUrls = newFeedImages.map(imageUploader::upload)

        feedRepository.update(editFeed, imageUrls)
    }

    fun remove(id: Long) {
        feedRepository.delete(id)
        feedImageRepository.deleteByFeedId(id)
    }
}