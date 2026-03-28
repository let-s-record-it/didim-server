package com.didim.dbmain.member.repository

import com.didim.domain.member.dataaccess.MemberRepository
import com.didim.domain.member.domain.Member
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
internal class MemberEntityRepository(
    private val memberCustomRepository: MemberCustomRepository,
    private val memberJpaRepository: MemberJpaRepository,
): MemberRepository {

    override fun findByMemberKey(memberKey: String): Member? {
        return memberCustomRepository.findByMemberKey(memberKey)?.toDomain()
    }
}