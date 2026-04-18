package com.didim.domain.alarm.domain

enum class AlarmType(
    val title: String,
    val body: String,
) {
    SCHEDULE_ADD("일정이 추가되었습니다.", "%s %s"),
    SCHEDULE_DELETE("일정이 삭제되었습니다.", "%s %s"),
    SCHEDULE_ALARM("일정이 있습니다.", "%s %s"),
    INVITE("캘린더에 초대되었습니다.", "%s"),
    FOLLOWING("%s님이 나를 팔로우했습니다.", ""),
    FEED_LIKE("%s님이 내 피드에 좋아요를 눌렀습니다.", ""),
}