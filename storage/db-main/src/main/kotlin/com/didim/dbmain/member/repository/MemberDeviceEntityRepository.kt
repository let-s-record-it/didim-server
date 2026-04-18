package com.didim.dbmain.member.repository

import com.didim.dbmain.member.entity.MemberDeviceEntity
import com.didim.domain.member.dataaccess.MemberDeviceRepository
import com.didim.domain.member.domain.MemberDevice
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class MemberDeviceEntityRepository(
    private val memberDeviceCustomRepository: MemberDeviceCustomRepository,
) : MemberDeviceRepository {

    override fun findByMemberKey(memberKey: String): List<MemberDevice> =
        memberDeviceCustomRepository.findByMemberKey(memberKey).map(MemberDeviceEntity::toDomain)

    override fun updateFcmToken(deviceId: String, fcmToken: String, memberKey: String) {
        updateFcmToken(deviceId, fcmToken, memberKey)
    }
}