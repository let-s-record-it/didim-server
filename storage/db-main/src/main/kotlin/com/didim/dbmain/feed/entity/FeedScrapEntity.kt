package com.didim.dbmain.feed.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "feed_scrap")
internal class FeedScrapEntity(
    @Column(nullable = false)
    var feedId: Long,
    @Column(nullable = false)
    var memberKey: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_scrap_id")
    var id: Long? = null,
) {
}