package com.didim.api.member.dto.response

/**
 *
 * @param weightRepPersonalId:
 *            추천 후보를 팔로우하는 대표 Member Id
 * @param weightRepName:
 *            추천 후보를 팔로우하는 대표 Member Name
 * @param weightCount:
 *            추천 후보를 팔로우하는 Member 수
 * @param commonFollowCount:
 *            추천 후보와 공통으로 팔로우하는 수
 */
data class FollowRecommendResponse(
    val internalId: Long,
    val personalId: String,
    val name: String,
    val profileImageUrl: String,
    val weightRepPersonalId: String,
    val weightRepName: String,
    val weightCount: Long,
    val commonFollowCount: Long,
)
