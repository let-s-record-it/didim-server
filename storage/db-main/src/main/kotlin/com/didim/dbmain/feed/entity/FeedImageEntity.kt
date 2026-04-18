package com.didim.dbmain.feed.entity

import com.didim.dbmain.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "feed_image")
@Entity
internal class FeedImageEntity(
    @Column(nullable = false)
    var imageUrl: String,
    @Column(nullable = false)
    var feedId: Long,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,
) : BaseEntity()