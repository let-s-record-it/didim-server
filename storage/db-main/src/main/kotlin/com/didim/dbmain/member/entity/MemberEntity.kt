package com.didim.dbmain.member.entity

import com.didim.dbmain.base.BaseEntity
import com.didim.domain.member.domain.EditMember
import com.didim.domain.member.domain.Member
import com.didim.domain.member.domain.MemberRole
import com.didim.domain.member.domain.NewMember
import com.didim.domain.member.domain.OAuthProvider
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Table(name = "member")
@Entity
internal class MemberEntity(
    @Column(nullable = false)
    var oauthAccount: String,
    @Column(nullable = false)
    var oauthProvider: OAuthProvider,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var email: String,
    @Column(nullable = false)
    var profileImageUrl: String,
    @Column(nullable = false)
    var activated: Boolean,
    @Column(nullable = false)
    var memberRole: List<MemberRole>,
    @Column(nullable = false)
    var job: String = "",
    @Column(nullable = false)
    var personalId: String = "",
    @Column(nullable = false)
    var memberKey: String = UUID.randomUUID().toString(),
    @Id @GeneratedValue
    val id: Long? = null,
) : BaseEntity() {
    companion object {
        fun from(newMember: NewMember) = MemberEntity(
            oauthAccount = newMember.oauthAccount,
            oauthProvider = newMember.oauthProvider,
            name = newMember.name,
            email = newMember.email,
            profileImageUrl = newMember.profileImageUrl,
            activated = false,
            memberRole = listOf(MemberRole.ROLE_USER)
        )
    }

    fun toDomain() = Member(
        id = id!!,
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
        activated = activated,
        roles = memberRole.toSet(),
    )

    fun update(editMember: EditMember) {
        this.name = editMember.name
        this.job = editMember.job
    }

    fun updateProfileImage(profileImageUrl: String) {
        this.profileImageUrl = profileImageUrl
    }

    fun activate(personalId: String) {
        this.personalId = personalId
        this.activated = true
    }
}