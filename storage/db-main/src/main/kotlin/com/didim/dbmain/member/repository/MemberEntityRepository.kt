package com.didim.dbmain.member.repository

import com.didim.dbmain.member.entity.MemberEntity
import com.didim.domain.auth.domain.OAuthAccount
import com.didim.domain.member.dataaccess.MemberRepository
import com.didim.domain.member.domain.EditMember
import com.didim.domain.member.domain.Member
import com.didim.domain.member.domain.NewMember
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class MemberEntityRepository(
    private val memberCustomRepository: MemberCustomRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : MemberRepository {
    override fun save(newMember: NewMember): String =
        memberJpaRepository.save(MemberEntity.from(newMember)).memberKey

    @Transactional(readOnly = true)
    override fun findByMemberKey(memberKey: String): Member? =
        memberCustomRepository.findByMemberKey(memberKey)?.toDomain()

    @Transactional(readOnly = true)
    override fun findByPersonalIdPrefix(personalIdPrefix: String): List<Member> =
        memberCustomRepository.findByPersonalIdPrefix(personalIdPrefix).map(MemberEntity::toDomain)

    @Transactional(readOnly = true)
    override fun findMemberKeyByOAuthAccount(oAuthAccount: OAuthAccount): String? =
        memberCustomRepository.findMemberKeyByOAuthAccount(oAuthAccount)

    @Transactional(readOnly = true)
    override fun existsByOAuthAccount(oAuthAccount: OAuthAccount): Boolean =
        memberCustomRepository.existsByOAuthAccount(oAuthAccount)

    override fun update(editMember: EditMember) {
        memberCustomRepository.findByMemberKey(editMember.memberKey)?.update(editMember)
    }

    override fun updateProfileImage(profileImageUrl: String, memberKey: String) {
        memberCustomRepository.findByMemberKey(memberKey)?.updateProfileImage(profileImageUrl)
    }

    override fun activate(personalId: String, memberKey: String) {
        memberCustomRepository.findByMemberKey(memberKey)?.activate(personalId)
    }

    override fun delete(memberKey: String) {
        memberCustomRepository.findByMemberKey(memberKey)?.delete()
    }

}