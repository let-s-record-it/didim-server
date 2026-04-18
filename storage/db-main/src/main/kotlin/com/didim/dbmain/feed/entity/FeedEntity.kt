package com.didim.dbmain.feed.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.feed.domain.EditFeed
import com.didim.domain.feed.domain.Feed
import com.didim.domain.feed.domain.FeedImages
import com.didim.domain.feed.domain.NewFeed
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "feed")
@Entity
internal class FeedEntity(
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var content: String,
    @Column(nullable = false)
    var likeCount: Long = 0L,
    @Column(nullable = false)
    var feedImageCount: Int = 0,
    @Column(nullable = false)
    var memberKey: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    var id: Long? = null,
) : BaseEntity() {
    companion object {
        fun from(newFeed: NewFeed) = FeedEntity(
            title = newFeed.titleValue,
            content = newFeed.contentValue,
            memberKey = newFeed.memberKey,
        )
    }

    fun toDomain(feedImages: FeedImages) = Feed.of(
        id = id!!,
        title = title,
        content = content,
        createdAt = createdAt,
        likeCount = likeCount,
        feedImages = feedImages,
        memberKey = memberKey,
    )

    fun update(editFeed: EditFeed) {
        this.title = editFeed.titleValue
        this.content = editFeed.contentValue
    }
}