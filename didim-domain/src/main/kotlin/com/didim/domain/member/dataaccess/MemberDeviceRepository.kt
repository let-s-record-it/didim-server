package com.didim.domain.member.dataaccess

import com.didim.domain.member.domain.MemberDevice

interface MemberDeviceRepository {
    fun findByMemberKey(memberKey: String): List<MemberDevice>

    fun updateFcmToken(deviceId: String, fcmToken: String, memberKey: String)
}