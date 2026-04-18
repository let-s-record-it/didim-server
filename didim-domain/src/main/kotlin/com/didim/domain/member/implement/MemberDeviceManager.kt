package com.didim.domain.member.implement

import com.didim.domain.member.dataaccess.MemberDeviceRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MemberDeviceManager(
    private val memberDeviceRepository: MemberDeviceRepository,
) {

    fun findByMemberKey(memberKey: String) = memberDeviceRepository.findByMemberKey(memberKey)

    fun updateFcmToken(deviceId: String, fcmToken: String, memberKey: String) =
        memberDeviceRepository.updateFcmToken(deviceId, fcmToken, memberKey)
}