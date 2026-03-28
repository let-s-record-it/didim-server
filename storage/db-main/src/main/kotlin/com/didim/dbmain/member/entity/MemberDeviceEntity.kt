package com.didim.dbmain.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Table(name = "member_device")
@Entity
class MemberDeviceEntity(
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
) {
}