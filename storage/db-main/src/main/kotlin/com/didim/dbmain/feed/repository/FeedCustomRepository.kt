package com.didim.dbmain.feed.repository

import com.didim.common.pagination.Page
import com.didim.common.pagination.Pageable
import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.feed.entity.FeedEntity
import com.didim.dbmain.feed.entity.FeedImageEntity
import com.didim.dbmain.feed.entity.QFeedEntity.feedEntity
import com.didim.dbmain.feed.entity.QFeedImageEntity.feedImageEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import com.didim.domain.feed.domain.Feed
import com.didim.domain.feed.domain.FeedImage
import com.didim.domain.feed.domain.FeedImages
import com.querydsl.core.group.GroupBy.groupBy
import com.querydsl.core.group.GroupBy.list
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class FeedCustomRepository : QuerydslRepositorySupport(FeedEntity::class) {

    fun findById(id: Long): FeedEntity? =
        selectFrom(feedEntity)
            .where(
                feedEntity.id.eq(id),
            )
            .fetchOne()

    fun findDomainById(id: Long): Feed? {
        val result: Map<FeedEntity, List<FeedImageEntity>> = queryFactory
            .from(feedEntity)
            .leftJoin(feedImageEntity)
            .on(feedEntity.id.eq(feedImageEntity.feedId))
            .where(feedEntity.id.eq(id))
            .transform(
                groupBy(feedEntity).`as`(
                    list(feedImageEntity)
                )
            )

        val entry = result.entries.firstOrNull() ?: return null
        val (feed, images) = entry.toPair()
        return feed.toDomain(FeedImages(images.map { FeedImage.of(it.imageUrl, feed.id!!, it.id!!) }))
    }

    fun findOrderByCreatedAtDesc(pageable: Pageable): Page<FeedEntity> =
        selectFrom(feedEntity)
            .where(feedEntity.status.eq(EntityStatus.ACTIVE))
            .orderBy(feedEntity.createdAt.desc())
            .offset(pageable.offset)
            .limit(pageable.limit + 1)
            .fetch()
            .toPage(pageable)

    fun findDomainOrderByCreatedAtDesc(pageable: Pageable): Page<Feed> {
        // 1. Feed 엔티티 조회
        val feedEntities = selectFrom(feedEntity)
            .where(feedEntity.status.eq(EntityStatus.ACTIVE))
            .orderBy(feedEntity.createdAt.desc())
            .offset(pageable.offset)
            .limit(pageable.limit + 1)
            .fetch()

        if (feedEntities.isEmpty()) {
            return Page(emptyList(), pageable, false)
        }

        val hasNext = feedEntities.size > pageable.limit.toInt()
        val actualFeeds = if (hasNext) feedEntities.dropLast(1) else feedEntities
        val feedIds = actualFeeds.map { it.id!! }

        // 2. FeedImage만 별도 조회
        val images = queryFactory
            .selectFrom(feedImageEntity)
            .where(feedImageEntity.feedId.`in`(feedIds))
            .fetch()
            .groupBy { it.feedId }

        // 3. 도메인 변환
        val feeds = actualFeeds.map { feed ->
            val feedImages = images[feed.id]?.map { FeedImage.of(it.imageUrl, feed.id!!, it.id!!) } ?: emptyList()
            feed.toDomain(FeedImages(feedImages))
        }

        return Page(feeds, pageable, hasNext)
    }

    fun findByMemberKeyOrderByCreatedAtDesc(pageable: Pageable, memberKey: String): Page<FeedEntity> =
        selectFrom(feedEntity)
            .where(
                feedEntity.memberKey.eq(memberKey),
                feedEntity.status.eq(EntityStatus.ACTIVE),
            )
            .orderBy(feedEntity.createdAt.desc())
            .offset(pageable.offset)
            .limit(pageable.limit + 1)
            .fetch()
            .toPage(pageable)

    fun findDomainByMemberKeyOrderByCreatedAtDesc(pageable: Pageable, memberKey: String): Page<Feed> {
        // 1. Feed 엔티티 조회
        val feedEntities = selectFrom(feedEntity)
            .where(
                feedEntity.memberKey.eq(memberKey),
                feedEntity.status.eq(EntityStatus.ACTIVE),
            )
            .orderBy(feedEntity.createdAt.desc())
            .offset(pageable.offset)
            .limit(pageable.limit + 1)
            .fetch()

        if (feedEntities.isEmpty()) {
            return Page(emptyList(), pageable, false)
        }

        val hasNext = feedEntities.size > pageable.limit.toInt()
        val actualFeeds = if (hasNext) feedEntities.dropLast(1) else feedEntities
        val feedIds = actualFeeds.map { it.id!! }

        // 2. FeedImage만 별도 조회
        val images = queryFactory
            .selectFrom(feedImageEntity)
            .where(feedImageEntity.feedId.`in`(feedIds))
            .fetch()
            .groupBy { it.feedId }

        // 3. 도메인 변환
        val feeds = actualFeeds.map { feed ->
            val feedImages = images[feed.id]?.map { FeedImage.of(it.imageUrl, feed.id!!, it.id!!) } ?: emptyList()
            feed.toDomain(FeedImages(feedImages))
        }

        return Page(feeds, pageable, hasNext)
    }
}