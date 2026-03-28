package com.didim.dbmain.member.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.member.entity.MemberEntity
import com.didim.dbmain.member.entity.QMemberEntity.memberEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
internal class MemberCustomRepository : QuerydslRepositorySupport(MemberEntity::class) {

    fun findByMemberKey(memberKey: String): MemberEntity? =
        selectFrom(memberEntity)
            .where(
                memberEntity.memberKey.eq(memberKey),
                memberEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetchOne()
}