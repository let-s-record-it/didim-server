package com.didim.dbmain.member.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.member.domain.MemberDevice
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Table(name = "member_device")
@Entity
internal class MemberDeviceEntity(
    @Column(nullable = false)
    var identifier: String,
    @Column(nullable = false)
    var model: String,
    @Column(nullable = false)
    var fcmToken: String,
    @Column(nullable = false)
    var memberKey: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_device_id")
    var id: Long? = null,
): BaseEntity() {
    fun toDomain() = MemberDevice(
        identifier = identifier,
        model = model,
        fcmToken = fcmToken,
        memberKey = memberKey,
        id = id,
    )
}