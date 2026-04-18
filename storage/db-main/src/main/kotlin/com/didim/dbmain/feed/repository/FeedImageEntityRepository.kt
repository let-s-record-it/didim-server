package com.didim.dbmain.feed.repository

import com.didim.dbmain.feed.entity.FeedImageEntity
import com.didim.domain.feed.dataaccess.FeedImageRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class FeedImageEntityRepository(
    private val feedImageJpaRepository: FeedImageJpaRepository,
    private val feedImageCustomRepository: FeedImageCustomRepository,
) : FeedImageRepository {

    override fun saveAll(newFeedImageUrls: List<String>, feedId: Long): List<Long> {
        val feedImages = newFeedImageUrls.map { FeedImageEntity(it, feedId) }
        return feedImageJpaRepository.saveAll(feedImages).map { it.id!! }
    }

    override fun delete(ids: List<Long>) = feedImageCustomRepository.deleteByIds(ids)

    override fun deleteByFeedId(feedId: Long) = feedImageCustomRepository.deleteByFeedId(feedId)
}