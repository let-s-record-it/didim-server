package com.didim.dbmain.feed.repository

import com.didim.dbmain.feed.entity.FeedImageEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface FeedImageJpaRepository : JpaRepository<FeedImageEntity, Long> {
}