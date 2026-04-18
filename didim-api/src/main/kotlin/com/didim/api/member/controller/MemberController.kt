package com.didim.api.member.controller

import com.didim.api.feed.dto.response.FeedCommentInListResponse
import com.didim.api.member.dto.request.ProfileModifyRequest
import com.didim.api.member.dto.request.TokenUpdateRequest
import com.didim.api.member.dto.response.FollowRecommendResponse
import com.didim.api.member.dto.response.MemberListResponse
import com.didim.api.member.dto.response.ProfileResponse
import com.didim.api.support.response.PageResponse
import com.didim.api.support.security.annotation.AuthMember
import com.didim.api.support.util.toImageData
import com.didim.common.pagination.Pageable
import com.didim.domain.member.business.MemberDeviceService
import com.didim.domain.member.business.MemberService
import com.didim.domain.member.domain.Member
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.function.Consumer

@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService,
    private val memberDeviceService: MemberDeviceService,
) {

    @GetMapping("/me")
    fun myProfileDetails(@AuthMember member: Member): ResponseEntity<ProfileResponse> {
        return ResponseEntity.ok(ProfileResponse.from(member))
    }

    @GetMapping("/me/comments")
    fun myCommentList(
        @RequestParam pageable: Pageable, @AuthMember member: Member
    ): ResponseEntity<PageResponse<FeedCommentInListResponse>> {
        return ResponseEntity.ok(
            feedCommentQueryService.searchByMemberIdOldCreated(pageable, member.getId())
        )
    }

    @GetMapping("/search")
    fun searchMemberList(
        @RequestParam personalId: String
    ): ResponseEntity<List<MemberListResponse>> {
        return ResponseEntity.ok(
            memberService.findByPersonalIdPrefix(personalId)
                .map(MemberListResponse::from)
        )
    }

    @GetMapping("/{memberKey}")
    fun profileDetails(
        @PathVariable memberKey: String, @AuthMember member: Member
    ): ResponseEntity<ProfileResponse> {
        return ResponseEntity.ok(
            ProfileResponse.from(memberService.findOtherMember(memberKey, member.memberKey))
        )
    }

    @GetMapping("/me/recommends/members")
    fun recommendMemberList(
        @AuthMember member: Member
    ): ResponseEntity<List<FollowRecommendResponse>> {
        val body: List<FollowRecommendResponse> =
            memberService.recommendFollows(member.personalId)
        body.forEach(Consumer { m: FollowRecommendResponse -> log.info("result: {}", m.personalId) })
        return ResponseEntity.ok(body)
    }

    @PostMapping("/{memberKey}/follow")
    fun follow(@PathVariable memberKey: String, @AuthMember member: Member): ResponseEntity<Unit> {
        memberService.follow(member.memberKey, memberKey)

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{memberKey}/unfollow")
    fun unfollow(
        @PathVariable memberKey: String, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        memberService.unfollow(member.memberKey, memberKey)

        return ResponseEntity.noContent().build()
    }

    @PutMapping("/me")
    fun profileModify(
        @RequestBody @Valid request: ProfileModifyRequest,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        memberService.modify(request.toEditMember(member.memberKey))

        return ResponseEntity.noContent().build()
    }

    @PutMapping("/me/image")
    @Throws(IOException::class)
    fun profileImageModify(
        @RequestPart newImage: MultipartFile, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        memberService.modifyProfileImage(newImage.toImageData(), member.memberKey)

        return ResponseEntity.noContent().build()
    }

    @PutMapping("/me/fcmToken")
    fun fcmTokenModify(
        @RequestBody request: TokenUpdateRequest, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        memberDeviceService.updateFcmToken(request.deviceId, request.fcmToken, member.memberKey)

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/me/withdraw")
    fun memberWithdrawal(@AuthMember member: Member): ResponseEntity<Unit> {
        memberService.withdraw(member.memberKey)

        return ResponseEntity.noContent().build()
    }
}