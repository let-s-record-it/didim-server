package com.didim.dbmain.feed.repository

import com.didim.dbmain.feed.entity.FeedLikeEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface FeedLikeJpaRepository : JpaRepository<FeedLikeEntity, Long> {

    fun existsByFeedIdAndMemberKey(feedId: Long, memberKey: String): Boolean

    fun deleteByFeedIdAndMemberKey(feedId: Long, memberKey: String): Int
}