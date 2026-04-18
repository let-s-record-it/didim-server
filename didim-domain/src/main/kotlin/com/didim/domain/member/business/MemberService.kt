package com.didim.domain.member.business

import com.didim.common.domain.ImageData
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.member.domain.EditMember
import com.didim.domain.member.domain.OtherMember
import com.didim.domain.member.implement.FollowManager
import com.didim.domain.member.implement.MemberManager
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberManager: MemberManager,
    private val followManager: FollowManager,
) {

    fun findByPersonalIdPrefix(personalIdPrefix: String) = memberManager.findByPersonalIdPrefix(personalIdPrefix)

    fun findOtherMember(memberKey: String, myMemberKey: String): OtherMember {
        return OtherMember(memberManager.findByMemberKey(memberKey), false)
    }

    fun follow(followerKey: String, followedKey: String) {
        followManager.follow(followerKey, followedKey)

        // TODO("알람 전송")
    }

    fun unfollow(followerKey: String, followedKey: String) {
        followManager.unfollow(followerKey, followedKey)

        // TODO("알람 전송")
    }

    fun modify(editMember: EditMember) {
        memberManager.modify(editMember)
    }

    fun modifyProfileImage(newImage: ImageData, memberKey: String) {
        memberManager.modifyProfileImage(newImage, memberKey)
    }

    fun withdraw(memberKey: String) {
        memberManager.withdraw(memberKey)
    }
}