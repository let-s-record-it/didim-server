package com.didim.domain.member.implement

import com.didim.common.domain.ImageData
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.auth.domain.OAuthAccount
import com.didim.domain.member.dataaccess.MemberRepository
import com.didim.domain.member.domain.EditMember
import com.didim.domain.member.domain.NewMember
import com.didim.domain.support.ImageUploader
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MemberManager(
    private val memberRepository: MemberRepository,
    private val imageUploader: ImageUploader,
) {

    fun register(newMember: NewMember) = memberRepository.save(newMember)

    fun findByMemberKey(memberKey: String) =
        memberRepository.findByMemberKey(memberKey) ?: throw AppException(ErrorType.MEMBER_NOT_FOUND)

    fun findByPersonalIdPrefix(personalIdPrefix: String) = memberRepository.findByPersonalIdPrefix(personalIdPrefix)

    fun exists(oauthAccount: OAuthAccount) = memberRepository.existsByOAuthAccount(oauthAccount)

    fun findMemberKey(oAuthAccount: OAuthAccount) = memberRepository.findMemberKeyByOAuthAccount(oAuthAccount)
        ?: throw AppException(ErrorType.MEMBER_NOT_FOUND)

    fun modify(editMember: EditMember) = memberRepository.update(editMember)

    fun modifyProfileImage(newImage: ImageData, memberKey: String) {
        imageUploader.upload(newImage).let {
            memberRepository.updateProfileImage(it, memberKey)
        }
    }

    fun activateMember(personalId: String, memberKey: String) = memberRepository.activate(personalId, memberKey)

    fun withdraw(memberKey: String) = memberRepository.delete(memberKey)
}