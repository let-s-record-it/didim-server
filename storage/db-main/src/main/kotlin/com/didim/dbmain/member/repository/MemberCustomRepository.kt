package com.didim.dbmain.member.repository

import com.didim.dbmain.base.EntityStatus
import com.didim.dbmain.member.entity.MemberEntity
import com.didim.dbmain.member.entity.QMemberEntity.memberEntity
import com.didim.dbmain.support.querydsl.QuerydslRepositorySupport
import com.didim.domain.auth.domain.OAuthAccount
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

    fun findByPersonalIdPrefix(personalIdPrefix: String): List<MemberEntity> =
        selectFrom(memberEntity)
            .where(
                memberEntity.personalId.startsWith(personalIdPrefix),
                memberEntity.status.eq(EntityStatus.ACTIVE),
            )
            .fetch()

    fun existsByOAuthAccount(oAuthAccount: OAuthAccount): Boolean =
        selectOne()
            .from(memberEntity)
            .where(
                memberEntity.oauthAccount.eq(oAuthAccount.account),
                memberEntity.oauthProvider.eq(oAuthAccount.provider),
                memberEntity.status.eq(EntityStatus.ACTIVE),
            ).fetchFirst() != null

    fun findMemberKeyByOAuthAccount(oAuthAccount: OAuthAccount): String? =
        select(memberEntity.memberKey)
            .from(memberEntity)
            .where(
                memberEntity.oauthAccount.eq(oAuthAccount.account),
                memberEntity.oauthProvider.eq(oAuthAccount.provider),
                memberEntity.status.eq(EntityStatus.ACTIVE),
            ).fetchOne()
}