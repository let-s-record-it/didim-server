package com.didim.dbmain.feed.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.feed.entity.FeedImageEntity
import com.didim.dbmain.feed.entity.QFeedImageEntity.feedImageEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
@Transactional
internal class FeedImageCustomRepository : QuerydslRepositorySupport(FeedImageEntity::class) {

    fun deleteByIds(ids: List<Long>) {
        flush()

        update(feedImageEntity)
            .set(feedImageEntity.status, EntityStatus.DELETED)
            .set(feedImageEntity.deletedAt, LocalDateTime.now())
            .where(feedImageEntity.id.`in`(ids))
            .execute()

        clear()
    }

    fun deleteByFeedId(feedId: Long) {
        flush()

        update(feedImageEntity)
            .set(feedImageEntity.status, EntityStatus.DELETED)
            .set(feedImageEntity.deletedAt, LocalDateTime.now())
            .where(feedImageEntity.feedId.eq(feedId))
            .execute()

        clear()
    }
}