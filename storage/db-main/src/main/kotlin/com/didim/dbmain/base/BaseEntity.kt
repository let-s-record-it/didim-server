package com.didim.dbmain.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
internal abstract class BaseEntity(
) {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())

    @LastModifiedDate
    @Column(nullable = false)
    var modifiedAt: LocalDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())

    @Column(nullable = false)
    var status: EntityStatus = EntityStatus.ACTIVE

    @Column
    var deletedAt: LocalDateTime? = null
}