package com.didim.domain.member.domain

class Member(
    val memberKey: String,
    val oauthAccount: String,
    val oauthProvider: OAuthProvider,
    val personalId: String,
    val name: String,
    val job: String,
    val email: String,
    val profileImageUrl: String,
    val followerCount: Long,
    val followingCount: Long,
    val roles: Set<MemberRole>,
    val activated: Boolean,
    val id: Long,
) {
}