package com.didim.domain.feed.dataaccess

interface FeedImageRepository {

    fun saveAll(newFeedImageUrls: List<String>, feedId: Long): List<Long>

    fun delete(ids: List<Long>)

    fun deleteByFeedId(feedId: Long)
}