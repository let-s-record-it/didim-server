package com.didim.domain.alarm.domain

data class FollowMessage(
    val followerId: Long,
    val title: String,
    val body: String,
)
