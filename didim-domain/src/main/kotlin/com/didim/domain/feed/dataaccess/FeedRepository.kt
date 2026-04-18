package com.didim.domain.feed.dataaccess

import com.didim.common.pagination.Page
import com.didim.common.pagination.Pageable
import com.didim.domain.feed.domain.EditFeed
import com.didim.domain.feed.domain.Feed
import com.didim.domain.feed.domain.NewFeed

interface FeedRepository {

    fun save(newFeed: NewFeed): Long

    fun findById(id: Long): Feed?

    fun findOrderByCreatedAtDesc(pageable: Pageable): Page<Feed>

    fun findByMemberKeyOrderByCreatedAtDesc(pageable: Pageable, memberKey: String): Page<Feed>

    fun update(editFeed: EditFeed, newFeedImageUrls: List<String>)

    fun delete(id: Long)
}