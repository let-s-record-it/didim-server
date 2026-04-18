package com.didim.domain.member.domain

data class MemberDevice(
    val identifier: String,
    val model: String,
    val fcmToken: String,
    val memberKey: String,
    val id: Long? = null,
)