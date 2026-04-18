package com.didim.domain.member.business

import com.didim.domain.member.implement.MemberDeviceManager
import org.springframework.stereotype.Service

@Service
class MemberDeviceService(
    private val memberDeviceManager: MemberDeviceManager,
) {

    fun updateFcmToken(deviceId: String, fcmToken: String, memberKey: String) =
        memberDeviceManager.updateFcmToken(deviceId, fcmToken, memberKey)
}