package com.didim.domain.member.business

import com.didim.domain.member.dataaccess.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
}