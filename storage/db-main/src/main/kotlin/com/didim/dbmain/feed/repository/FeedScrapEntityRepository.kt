package com.didim.dbmain.feed.repository

import com.didim.dbmain.feed.entity.FeedScrapEntity
import com.didim.domain.feed.dataaccess.FeedScrapRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class FeedScrapEntityRepository(
    private val feedScrapJpaRepository: FeedScrapJpaRepository,
) : FeedScrapRepository {

    override fun save(feedId: Long, memberKey: String) {
        feedScrapJpaRepository.save(FeedScrapEntity(feedId, memberKey))
    }

    override fun delete(feedId: Long, memberKey: String) {
        feedScrapJpaRepository.deleteByFeedIdAndMemberKey(feedId, memberKey)
    }

    override fun existsByFeedIdAndMemberKey(feedId: Long, memberKey: String): Boolean =
        feedScrapJpaRepository.existsByFeedIdAndMemberKey(feedId, memberKey)
}