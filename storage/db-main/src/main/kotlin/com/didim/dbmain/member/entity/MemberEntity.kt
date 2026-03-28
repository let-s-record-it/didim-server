package com.didim.dbmain.member.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.member.domain.Member
import com.didim.domain.member.domain.MemberRole
import com.didim.domain.member.domain.OAuthProvider
import jakarta.persistence.*

@Table(name = "member")
@Entity
internal class MemberEntity(
    @Column(nullable = false)
    var memberKey: String,
    @Column(nullable = false)
    var oauthAccount: String,
    @Column(nullable = false)
    var oauthProvider: OAuthProvider,
    @Column(nullable = false)
    var personalId: String,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var job: String,
    @Column(nullable = false)
    var email: String,
    @Column(nullable = false)
    var profileImageUrl: String,
    @Column(nullable = false)
    var activated: Boolean,
    @Column(nullable = false)
    var memberRole: List<MemberRole>,
    @Id @GeneratedValue
    val id: Long? = null,
) : BaseEntity() {
    fun toDomain() = Member(
        memberKey = memberKey,
        oauthAccount = oauthAccount,
        oauthProvider = oauthProvider,
        personalId = personalId,
        name = name,
        job = job,
        email = email,
        profileImageUrl = profileImageUrl,
        followerCount = 0,
        followingCount = 0,
    )
}