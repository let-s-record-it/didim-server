package com.didim.api.feed.controller

import com.didim.api.feed.dto.request.FeedAddRequest
import com.didim.api.feed.dto.request.FeedModifyRequest
import com.didim.api.feed.dto.response.FeedDetailsResponse
import com.didim.api.feed.dto.response.FeedInListResponse
import com.didim.api.support.response.PageResponse
import com.didim.api.support.security.annotation.AuthMember
import com.didim.api.support.util.toImageData
import com.didim.common.pagination.Pageable
import com.didim.domain.feed.business.FeedService
import com.didim.domain.member.business.MemberService
import com.didim.domain.member.domain.Member
import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.URI

@RestController
@RequestMapping("/api/v1/feeds")
class FeedController(
    private val feedService: FeedService,
    private val memberService: MemberService,
) {

    @PostMapping
    fun feedAdd(
        @Validated @RequestPart feedAddRequest: FeedAddRequest,
        @Valid @Size(max = 10) @RequestPart(required = false) images: MutableList<MultipartFile>,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        val feedId = feedService.addFeed(feedAddRequest.toNewFeed(member.memberKey), images.map { it.toImageData() })

        return ResponseEntity.created(URI.create("/api/v1/feeds/$feedId")).build()
    }

    @GetMapping("/{feedId}")
    fun feedDetails(
        @PathVariable feedId: Long, @AuthMember member: Member
    ): ResponseEntity<FeedDetailsResponse> {
        val feed = feedService.findFeed(feedId)
        val isLiked = feedService.isLiked(feed.id, member.memberKey)
        val isScraped = feedService.isScraped(feed.id, member.memberKey)
        val otherMember = memberService.findOtherMember(feed.memberKey, member.memberKey)
        return ResponseEntity.ok(FeedDetailsResponse.of(feed, isLiked, isScraped, otherMember.member))
    }

    @GetMapping
    fun feedList(
        pageable: Pageable, @AuthMember member: Member
    ): ResponseEntity<PageResponse<FeedInListResponse>> {
        return ResponseEntity.ok(feedService.searchRecentCreated(pageable, member.memberKey))
    }

    @GetMapping("/my-feed")
    fun myFeedList(
        pageable: Pageable, @AuthMember member: Member
    ): ResponseEntity<PageResponse<FeedInListResponse>> {
        return ResponseEntity.ok(
            feedService.searchRecentCreatedByMemberId(pageable, member.memberKey)
        )
    }

    @PutMapping("/{feedId}")
    @Throws(IOException::class)
    fun feedModify(
        @PathVariable feedId: Long,
        @Validated @RequestPart request: FeedModifyRequest,
        @Valid @Size(max = 10) @RequestPart(required = false) newImages: MutableList<MultipartFile>,
        @AuthMember member: Member
    ): ResponseEntity<Unit> {
        feedService.modifyFeed(request.toEditFeed(feedId, member.memberKey), newImages.map { it.toImageData() })
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{feedId}")
    fun feedRemove(@PathVariable feedId: Long): ResponseEntity<Unit> {
        feedService.removeFeed(feedId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{feedId}/like")
    fun feedLike(@PathVariable feedId: Long, @AuthMember member: Member): ResponseEntity<Unit> {
        feedService.feedLike(feedId, member.memberKey)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{feedId}/unlike")
    fun feedUnlike(
        @PathVariable feedId: Long, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        feedService.feedUnlike(feedId, member.memberKey)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{feedId}/scrap")
    fun feedScrap(@PathVariable feedId: Long, @AuthMember member: Member): ResponseEntity<Unit> {
        feedService.feedScrap(feedId, member.memberKey)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{feedId}/unscrap")
    fun feedUnScrap(
        @PathVariable feedId: Long, @AuthMember member: Member
    ): ResponseEntity<Unit> {
        feedService.feedUnScrap(feedId, member.memberKey)
        return ResponseEntity.noContent().build()
    }
}