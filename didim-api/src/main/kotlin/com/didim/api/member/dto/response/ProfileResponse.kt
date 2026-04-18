package com.didim.api.member.dto.response

import com.didim.domain.member.domain.Member
import com.didim.domain.member.domain.OtherMember

data class ProfileResponse(
    val id: Long,
    val name: String,
    val job: String,
    val personalId: String,
    val email: String,
    val profileImageUrl: String,
    val followerCount: Long,
    val followingCount: Long,
    val isFollowing: Boolean,
) {
    companion object {
        fun from(member: OtherMember) = ProfileResponse(
            id = member.id,
            name = member.name,
            job = member.job,
            personalId = member.personalId,
            email = member.email,
            profileImageUrl = member.profileImageUrl,
            followerCount = member.followerCount,
            followingCount = member.followingCount,
            isFollowing = member.isFollowing,
        )

        fun from(member: Member) = ProfileResponse(
            id = member.id,
            name = member.name,
            job = member.job,
            personalId = member.personalId,
            email = member.email,
            profileImageUrl = member.profileImageUrl,
            followerCount = member.followerCount,
            followingCount = member.followingCount,
            isFollowing = true,
        )
    }
}
