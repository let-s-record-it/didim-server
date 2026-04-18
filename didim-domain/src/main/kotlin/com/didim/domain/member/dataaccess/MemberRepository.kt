package com.didim.domain.member.dataaccess

import com.didim.domain.auth.domain.OAuthAccount
import com.didim.domain.member.domain.EditMember
import com.didim.domain.member.domain.Member
import com.didim.domain.member.domain.NewMember

interface MemberRepository {
    fun save(newMember: NewMember): String

    fun findByMemberKey(memberKey: String): Member?

    fun findByPersonalIdPrefix(personalIdPrefix: String): List<Member>

    fun findMemberKeyByOAuthAccount(oAuthAccount: OAuthAccount): String?

    fun existsByOAuthAccount(oAuthAccount: OAuthAccount): Boolean

    fun update(editMember: EditMember)

    fun updateProfileImage(profileImageUrl: String, memberKey: String)

    fun activate(personalId: String, memberKey: String)

    fun delete(memberKey: String)
}