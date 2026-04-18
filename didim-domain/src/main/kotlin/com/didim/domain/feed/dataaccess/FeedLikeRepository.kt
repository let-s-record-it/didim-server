package com.didim.domain.feed.dataaccess

interface FeedLikeRepository {

    fun save(feedId: Long, memberKey: String)

    fun delete(feedId: Long, memberKey: String)

    fun existsByFeedIdAndMemberKey(feedId: Long, memberKey: String): Boolean
}