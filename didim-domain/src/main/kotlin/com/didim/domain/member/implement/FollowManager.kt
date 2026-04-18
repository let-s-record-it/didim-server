package com.didim.domain.member.implement

import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class FollowManager {

    fun follow(followerKey: String, followedKey: String) {
        if (followerKey == followedKey) {
            throw AppException(ErrorType.INVALID_FOLLOW_REQUEST)
        }
//        memberManager.findByMemberKey(followerKey)
    }

    fun unfollow(followerKey: String, followedKey: String) {
        if (followerKey == followedKey) {
            throw AppException(ErrorType.INVALID_UNFOLLOW_REQUEST)
        }
//        memberManager.findByMemberKey(followerKey)
    }
}