package com.didim.dbmain.feed.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "feed_comment")
@Entity
class FeedCommentEntity(
    @Column(nullable = false)
    var content: String,
    @Column(nullable = false)
    var feedId: Long,
    @Column(nullable = false)
    var memberKey: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_comment_id")
    var id: Long? = null,
) {
}