package com.didim.domain.member.dataaccess

import com.didim.domain.member.domain.Member

interface MemberRepository {
    fun findByMemberKey(memberKey: String): Member?
}