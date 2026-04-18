package com.didim.dbmain.feed.repository

import com.didim.dbmain.feed.entity.FeedLikeEntity
import com.didim.domain.feed.dataaccess.FeedLikeRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class FeedLikeEntityRepository(
    private val feedLikeJpaRepository: FeedLikeJpaRepository,
) : FeedLikeRepository {

    override fun save(feedId: Long, memberKey: String) {
        feedLikeJpaRepository.save(FeedLikeEntity(feedId, memberKey))
    }

    override fun delete(feedId: Long, memberKey: String) {
        feedLikeJpaRepository.deleteByFeedIdAndMemberKey(feedId, memberKey)
    }

    override fun existsByFeedIdAndMemberKey(feedId: Long, memberKey: String): Boolean =
        feedLikeJpaRepository.existsByFeedIdAndMemberKey(feedId, memberKey)
}