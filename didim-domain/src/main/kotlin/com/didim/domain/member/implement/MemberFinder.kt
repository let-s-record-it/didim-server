package com.didim.domain.member.implement

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.member.dataaccess.MemberRepository
import org.springframework.stereotype.Component

@Component
class MemberFinder(
    private val memberRepository: MemberRepository,
) {

    fun find(memberKey: String) = memberRepository.findByMemberKey(memberKey)
        ?: throw AppException(ErrorType.NOT_FOUND_DATA)
}