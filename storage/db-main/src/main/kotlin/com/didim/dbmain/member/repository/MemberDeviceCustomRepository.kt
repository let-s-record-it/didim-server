package com.didim.dbmain.member.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.member.entity.MemberDeviceEntity
import com.didim.dbmain.member.entity.QMemberDeviceEntity.memberDeviceEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class MemberDeviceCustomRepository : QuerydslRepositorySupport(MemberDeviceEntity::class) {

    fun findByMemberKey(memberKey: String): List<MemberDeviceEntity> =
        selectFrom(memberDeviceEntity)
            .where(
                memberDeviceEntity.memberKey.eq(memberKey),
                memberDeviceEntity.status.eq(EntityStatus.ACTIVE)
            )
            .fetch()

    fun updateFcmToken(deviceId: String, fcmToken: String, memberKey: String) {
        flush()

        update(memberDeviceEntity)
            .set(memberDeviceEntity.fcmToken, fcmToken)
            .where(
                memberDeviceEntity.identifier.eq(deviceId),
                memberDeviceEntity.memberKey.eq(memberKey),
                memberDeviceEntity.status.eq(EntityStatus.ACTIVE),
            )
            .execute()

        clear()
    }
}