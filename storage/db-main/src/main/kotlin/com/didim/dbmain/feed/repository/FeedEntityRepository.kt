package com.didim.dbmain.feed.repository

import com.didim.common.pagination.Page
import com.didim.common.pagination.Pageable
import com.didim.dbmain.feed.entity.FeedEntity
import com.didim.domain.feed.dataaccess.FeedRepository
import com.didim.domain.feed.domain.EditFeed
import com.didim.domain.feed.domain.Feed
import com.didim.domain.feed.domain.NewFeed
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class FeedEntityRepository(
    private val feedJpaRepository: FeedJpaRepository,
    private val feedCustomRepository: FeedCustomRepository,
) : FeedRepository {

    override fun save(newFeed: NewFeed): Long = feedJpaRepository.save(FeedEntity.from(newFeed)).id!!

    override fun findById(id: Long): Feed? = feedCustomRepository.findDomainById(id)

    override fun findOrderByCreatedAtDesc(pageable: Pageable): Page<Feed> {
        feedCustomRepository.findOrderByCreatedAtDesc(pageable).map { it.toDomain() }
    }

    override fun findByMemberKeyOrderByCreatedAtDesc(
        pageable: Pageable,
        memberKey: String
    ): Page<Feed> {
        feedCustomRepository.findByMemberKeyOrderByCreatedAtDesc(pageable, memberKey)
    }

    override fun update(editFeed: EditFeed, newFeedImageUrls: List<String>) {
        feedCustomRepository.findById(editFeed.id)?.update(editFeed)
    }

    override fun delete(id: Long) {
        feedCustomRepository.findById(id)?.delete()
    }
}