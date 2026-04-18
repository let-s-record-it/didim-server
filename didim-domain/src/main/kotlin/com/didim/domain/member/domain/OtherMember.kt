package com.didim.domain.member.domain

data class OtherMember(
    val member: Member,
    val isFollowing: Boolean,
) {
    val id get() = member.id
    val name get() = member.name
    val job get() = member.job
    val personalId get() = member.personalId
    val email get() = member.email
    val profileImageUrl get() = member.profileImageUrl
    val followerCount get() = member.followerCount
    val followingCount get() = member.followingCount
}