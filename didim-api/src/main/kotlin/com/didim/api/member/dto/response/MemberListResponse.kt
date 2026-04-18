package com.didim.api.member.dto.response

import com.didim.domain.member.domain.Member

data class MemberListResponse(
    val id: Long,
    val personalId: String,
    val name: String,
    val profileImageUrl: String,
) {
    companion object {
        fun from(member: Member) = MemberListResponse(
            id = member.id,
            personalId = member.personalId,
            name = member.name,
            profileImageUrl = member.profileImageUrl,
        )
    }
}