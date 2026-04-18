package com.didim.dbmain.feed.repository

import com.didim.dbmain.feed.entity.FeedEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface FeedJpaRepository : JpaRepository<FeedEntity, Long> {
}