package com.didim.dbmain.feed.repository

import com.didim.dbmain.feed.entity.FeedScrapEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface FeedScrapJpaRepository : JpaRepository<FeedScrapEntity, Long> {

    fun existsByFeedIdAndMemberKey(feedId: Long, memberKey: String): Boolean

    fun deleteByFeedIdAndMemberKey(feedId: Long, memberKey: String): Int
}