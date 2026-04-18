package com.didim.common.exception

import org.springframework.boot.logging.LogLevel

enum class ErrorType(
    val status: HttpStatus,
    val errorCode: ErrorCode,
    val message: String,
    val logLevel: LogLevel
) {

    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "알 수 없는 오류가 발생했습니다.", LogLevel.ERROR),
    REQUIRED_AUTH(HttpStatus.UNAUTHORIZED, ErrorCode.E401, "리소스에 접근하기 위한 인증이 필요합니다.", LogLevel.WARN),
    FAILED_AUTH(HttpStatus.BAD_REQUEST, ErrorCode.E400, "인증에 실패했습니다.", LogLevel.WARN),
    INVALID_ACCESS_PATH(HttpStatus.BAD_REQUEST, ErrorCode.E400, "잘못된 접근 경로입니다.", LogLevel.WARN),
    NOT_FOUND_DATA(HttpStatus.BAD_REQUEST, ErrorCode.E400, "해당 데이터를 찾을 수 없습니다.", LogLevel.WARN),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, ErrorCode.E429, "너무 많은 요청을 보냈습니다.", LogLevel.WARN),

    FILE_GENERATE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "새 파일을 생성할 수 없습니다.", LogLevel.ERROR),
    FILE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "파일을 찾을 수 없습니다.", LogLevel.ERROR),

    IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "이미지 업로드에 실패했습니다.", LogLevel.ERROR),
    INVALID_IMAGE_TYPE(HttpStatus.BAD_REQUEST, ErrorCode.E400, "지원하지 않는 이미지 형식입니다.", LogLevel.WARN),
    INVALID_IMAGE_FILE_NAME(HttpStatus.BAD_REQUEST, ErrorCode.E400, "이미지 파일명이 비어있습니다.", LogLevel.WARN),

    // AUTH
    ID_TOKEN_UNSUPPORTED(HttpStatus.BAD_REQUEST, ErrorCode.E1000, "지원되지 않는 ID Token 입니다.", LogLevel.WARN),
    ID_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, ErrorCode.E1001, "ID Token이 만료되었습니다.", LogLevel.WARN),
    ID_TOKEN_INVALID_KEY(HttpStatus.BAD_REQUEST, ErrorCode.E1002, "App Key가 유효하지 않습니다.", LogLevel.WARN),
    ID_TOKEN_INVALID_SIGNATURE(HttpStatus.BAD_REQUEST, ErrorCode.E1003, "ID Token의 Signature가 유효하지 않습니다.", LogLevel.WARN),
    AUTHENTICATION_REQUIRED(HttpStatus.BAD_REQUEST, ErrorCode.E1004, "해당 리소스에 접근하기 위한 인증이 필요합니다.", LogLevel.WARN),
    AUTHENTICATION_FAILED(HttpStatus.BAD_REQUEST, ErrorCode.E1005, "인증에 실패했습니다.", LogLevel.WARN),
    JWT_MALFORMED(HttpStatus.BAD_REQUEST, ErrorCode.E1006, "JWT가 손상되었습니다.", LogLevel.WARN),
    JWT_EXPIRED(HttpStatus.BAD_REQUEST, ErrorCode.E1007, "JWT가 만료되었습니다.", LogLevel.WARN),
    JWT_UNSUPPORTED(HttpStatus.BAD_REQUEST, ErrorCode.E1008, "지원되지 않는 JWT 형식입니다.", LogLevel.WARN),
    JWT_INVALID_SIGNATURE(HttpStatus.BAD_REQUEST, ErrorCode.E1009, "JWT의 Signature가 유효하지 않습니다.", LogLevel.WARN),
    INVALID_KAKAO_TOKEN(HttpStatus.BAD_REQUEST, ErrorCode.E1010, "유효하지 않은 Kakao Access Token입니다.", LogLevel.WARN),
    INVALID_TOKEN_METHOD(HttpStatus.BAD_REQUEST, ErrorCode.E1011, "토큰 방식이 올바르지 않습니다.", LogLevel.WARN),
    INVALID_TOKEN_TYPE(HttpStatus.BAD_REQUEST, ErrorCode.E1012, "토큰 타입이 올바르지 않습니다.", LogLevel.WARN),

    // MEMBER
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E2000, "요청한 멤버를 찾을 수 없습니다.", LogLevel.WARN),
    CAN_NOT_REJOIN(HttpStatus.BAD_REQUEST, ErrorCode.E2001, "재가입 할 수 없는 멤버입니다.", LogLevel.WARN),
    INVALID_MEMBER_KEY(HttpStatus.BAD_REQUEST, ErrorCode.E2002, "멤버 key가 유효하지 않습니다.", LogLevel.WARN),
    INVALID_FOLLOW_REQUEST(HttpStatus.BAD_REQUEST, ErrorCode.E2003, "유효하지 않은 팔로우 요청입니다.", LogLevel.WARN),
    INVALID_UNFOLLOW_REQUEST(HttpStatus.BAD_REQUEST, ErrorCode.E2004, "유효하지 않은 팔로우 취소 요청입니다.", LogLevel.WARN),

    // CALENDAR
    NULL_CALENDAR_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E3000, "캘린더 제목은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_CALENDAR_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E3001, "캘린더 제목은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_CALENDAR_TITLE_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E3002, "캘린더 제목의 길이는 30자를 넘을 수 없습니다.", LogLevel.WARN),
    INVALID_CALENDAR_COLOR_HEX(HttpStatus.BAD_REQUEST, ErrorCode.E3003, "유효하지 않은 캘린더 색상 값입니다.", LogLevel.WARN),
    NULL_CALENDAR_COLOR_HEX(HttpStatus.BAD_REQUEST, ErrorCode.E3004, "캘린더 색상 값은 null일 수 없습니다.", LogLevel.WARN),
    CALENDAR_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E3005, "요청한 캘린더를 찾을 수 없습니다.", LogLevel.WARN),
    INVALID_CALENDAR_GET_REQUEST(HttpStatus.BAD_REQUEST, ErrorCode.E3006, "유효하지 않은 캘린더 조회 요청입니다.", LogLevel.WARN),
    CALENDAR_ACCESS_DENIED(HttpStatus.BAD_REQUEST, ErrorCode.E3007, "해당 사용자는 접근할 수 없는 캘린더입니다.", LogLevel.WARN),

    // CALENDAR_CATEGORY
    NULL_CALENDAR_CATEGORY_NAME(HttpStatus.BAD_REQUEST, ErrorCode.E4000, "캘린더 카테고리 이름은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_CALENDAR_CATEGORY_NAME(HttpStatus.BAD_REQUEST, ErrorCode.E4001, "캘린더 카테고리 이름은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_CALENDAR_CATEGORY_NAME_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E4002, "캘린더 카테고리 이름의 길이는 10자를 넘을 수 없습니다.", LogLevel.WARN),
    CALENDAR_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E4003, "캘린더 카테고리를 찾을 수 없습니다.", LogLevel.WARN),
    INVALID_CALENDAR_CATEGORY_GET_REQUEST(HttpStatus.BAD_REQUEST, ErrorCode.E4004, "유효하지 않은 캘린더 카테고리 조회 요청입니다.", LogLevel.WARN),

    // CALENDAR_MEMBER
    CALENDAR_MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E5000, "캘린더 멤버를 찾을 수 없습니다.", LogLevel.WARN),
    INVALID_CALENDAR_MEMBER_GET_REQUEST(HttpStatus.BAD_REQUEST, ErrorCode.E5001, "유효하지 않은 캘린더 멤버 조회 요청입니다.", LogLevel.WARN),

    // SCHEDULE
    NULL_SCHEDULE_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E6000, "일정 제목은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_SCHEDULE_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E6001, "일정 제목은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_SCHEDULE_TITLE_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E6002, "일정 제목의 길이는 30자를 넘을 수 없습니다.", LogLevel.WARN),
    NULL_SCHEDULE_DESCRIPTION(HttpStatus.BAD_REQUEST, ErrorCode.E6003, "일정 설명은 null일 수 없습니다.", LogLevel.WARN),
    INVALID_SCHEDULE_DESCRIPTION_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E6004, "설명의 길이는 500자를 넘을 수 없습니다.", LogLevel.WARN),
    NULL_SCHEDULE_COLOR_HEX(HttpStatus.BAD_REQUEST, ErrorCode.E6005, "일정 색상 값은 null일 수 없습니다.", LogLevel.WARN),
    INVALID_SCHEDULE_COLOR_HEX(HttpStatus.BAD_REQUEST, ErrorCode.E6006, "유효하지 않은 일정 색상 값입니다.", LogLevel.WARN),
    LATITUDE_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, ErrorCode.E6007, "위도 값은 0 이상 90 이하여야 합니다.", LogLevel.WARN),
    LONGITUDE_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, ErrorCode.E6008, "경도 값은 0 이상 180 이하여야 합니다.", LogLevel.WARN),
    INVALID_DURATION(HttpStatus.BAD_REQUEST, ErrorCode.E6009, "일정 시작 시간은 종료 시간보다 클 수 없습니다.", LogLevel.WARN),
    INVALID_REPETITION_PERIOD(HttpStatus.BAD_REQUEST, ErrorCode.E6010, "일정 반복 주기는 1 이상 999 이하여야 합니다.", LogLevel.WARN),
    INVALID_MONTH_OF_YEAR(HttpStatus.BAD_REQUEST, ErrorCode.E6011, "월은 1 이상 12 이하여야 입니다.", LogLevel.WARN),
    INVALID_DAY_OF_MONTH(HttpStatus.BAD_REQUEST, ErrorCode.E6012, "유효하지 않은 일입니다.", LogLevel.WARN),
    INVALID_REPETITION_TYPE(HttpStatus.BAD_REQUEST, ErrorCode.E6013, "유효하지 않은 반복 타입입니다.", LogLevel.WARN),
    WEEKDAY_BIT_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, ErrorCode.E6014, "요일 비트는 1 이상 127 이하여야 합니다.", LogLevel.WARN),
    NOT_EQUAL_DAY_OF_MONTH(HttpStatus.BAD_REQUEST, ErrorCode.E6015, "dayOfMonth와 startDate의 일이 일치하지 않습니다.", LogLevel.WARN),
    NOT_EQUAL_MONTH_OF_YEAR(HttpStatus.BAD_REQUEST, ErrorCode.E6016, "monthOfYear와 startDate의 월이 일치하지 않습니다.", LogLevel.WARN),
    SCHEDULE_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E6017, "요청한 일정을 찾을 수 없습니다.", LogLevel.WARN),
    SCHEDULE_GROUP_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E6018, "요청한 일정 그룹을 찾을 수 없습니다.", LogLevel.WARN),
    REPETITION_PATTERN_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E6019, "요청한 반복 패턴을 찾을 수 없습니다.", LogLevel.WARN),

    // SCHEDULE_CATEGORY
    NULL_SCHEDULE_CATEGORY_NAME(HttpStatus.BAD_REQUEST, ErrorCode.E7000, "일정 카테고리 이름은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_SCHEDULE_CATEGORY_NAME(HttpStatus.BAD_REQUEST, ErrorCode.E7001, "일정 카테고리 이름은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_SCHEDULE_CATEGORY_NAME_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E7002, "일정 카테고리 이름의 길이는 10자를 넘을 수 없습니다.", LogLevel.WARN),
    SCHEDULE_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E7003, "일정 카테고리를 찾을 수 없습니다.", LogLevel.WARN),
    INVALID_SCHEDULE_CATEGORY_GET_REQUEST(HttpStatus.BAD_REQUEST, ErrorCode.E7004, "유효하지 않은 일정 카테고리 조회 요청입니다.", LogLevel.WARN),

    // GOAL
    MONTHLY_GOAL_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E8000, "존재하지 않는 월 목표입니다.", LogLevel.WARN),
    DIFFERENT_YEAR_MONTH(HttpStatus.BAD_REQUEST, ErrorCode.E8001, "월 목표 기간 시작일과 종료일의 년,월은 같아야 합니다.", LogLevel.WARN),
    INVALID_START_DAY_OF_MONTH(HttpStatus.BAD_REQUEST, ErrorCode.E8002, "월 목표 기간의 시작일은 1일이어야 합니다.", LogLevel.WARN),
    INVALID_END_DAY_OF_MONTH(HttpStatus.BAD_REQUEST, ErrorCode.E8003, "월 목표 기간의 종료일은 %d일이어야 합니다.", LogLevel.WARN),
    MONTHLY_GOAL_ACCESS_DENIED(HttpStatus.BAD_REQUEST, ErrorCode.E8004, "해당 사용자는 접근할 수 없는 월 목표입니다.", LogLevel.WARN),
    WEEKLY_GOAL_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E8005, "존재하지 않는 주 목표입니다.", LogLevel.WARN),
    INVALID_START_DAY_OF_WEEK(HttpStatus.BAD_REQUEST, ErrorCode.E8006, "주 목표 기간의 시작일은 일요일이어야 합니다.", LogLevel.WARN),
    INVALID_DIFFERENCE_OF_DATE(HttpStatus.BAD_REQUEST, ErrorCode.E8007, "주 목표 기간의 시작일과 종료일은 6일 차이여야 합니다.", LogLevel.WARN),
    WEEKLY_GOAL_ACCESS_DENIED(HttpStatus.BAD_REQUEST, ErrorCode.E8008, "해당 사용자는 접근할 수 없는 주 목표입니다.", LogLevel.WARN),
    WEEK_NOT_CONTAINS_DATE(HttpStatus.BAD_REQUEST, ErrorCode.E8009, "해당 주차에 존재하지 않는 날짜입니다.", LogLevel.WARN),
    RELATED_GOAL_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E8010, "해당 주 목표에 연관 목표가 존재하지 않습니다.", LogLevel.WARN),
    NULL_GOAL_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E8011, "목표 제목은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_GOAL_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E8012, "목표 제목은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_GOAL_TITLE_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E8013, "목표 제목의 길이는 30자를 넘을 수 없습니다.", LogLevel.WARN),
    NULL_GOAL_DESCRIPTION(HttpStatus.BAD_REQUEST, ErrorCode.E8014, "목표 설명은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_GOAL_DESCRIPTION(HttpStatus.BAD_REQUEST, ErrorCode.E8015, "목표 설명은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_GOAL_DESCRIPTION_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E8016, "목표 설명의 길이는 500자를 넘을 수 없습니다.", LogLevel.WARN),
    NULL_GOAL_COLOR_HEX(HttpStatus.BAD_REQUEST, ErrorCode.E8017, "일정 색상 값은 null일 수 없습니다.", LogLevel.WARN),
    INVALID_GOAL_COLOR_HEX(HttpStatus.BAD_REQUEST, ErrorCode.E8018, "유효하지 않은 일정 색상 값입니다.", LogLevel.WARN),
    NULL_GOAL_PERIOD(HttpStatus.BAD_REQUEST, ErrorCode.E8019, "목표 기간 시작일과 종료일은 null일 수 없습니다.", LogLevel.WARN),

    // TASK
    TASK_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E9000, "존재하지 않는 할 일입니다.", LogLevel.WARN),
    NULL_TASK_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E9001, "할 일 제목은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_TASK_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E9002, "할 일 제목은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_TASK_TITLE_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E9003, "할 일 제목의 길이는 30자를 넘을 수 없습니다.", LogLevel.WARN),
    NULL_TASK_DESCRIPTION(HttpStatus.BAD_REQUEST, ErrorCode.E9004, "할 일 제목은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_TASK_DESCRIPTION(HttpStatus.BAD_REQUEST, ErrorCode.E9005, "할 일 설명은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_TASK_DESCRIPTION_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E9006, "할 일 설명의 길이는 500자를 넘을 수 없습니다.", LogLevel.WARN),
    NULL_TASK_COLOR_HEX(HttpStatus.BAD_REQUEST, ErrorCode.E9007, "할 일 생상 값은 null일 수 없습니다.", LogLevel.WARN),
    INVALID_TASK_COLOR_HEX(HttpStatus.BAD_REQUEST, ErrorCode.E9008, "유효하지 않은 할 일 색상 값입니다.", LogLevel.WARN),
    NULL_TASK_DATE(HttpStatus.BAD_REQUEST, ErrorCode.E9009, "할 일 날짜는 null일 수 없습니다.", LogLevel.WARN),

    // TASK_REPETITION
    TASK_REPETITION_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E10000, "존재하지 않는 할 일 반복 패턴입니다.", LogLevel.WARN),
    NULL_TASK_REPETITION_PERIOD(HttpStatus.BAD_REQUEST, ErrorCode.E10001, "반복 주기는 null일 수 없습니다.", LogLevel.WARN),
    TASK_REPETITION_PERIOD_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, ErrorCode.E10002, "반복 주기는 1에서 999 사이여야 합니다.", LogLevel.WARN),
    NULL_TASK_REPETITION_DURATION(HttpStatus.BAD_REQUEST, ErrorCode.E10003, "반복 시작/종료일은 null일 수 없습니다.", LogLevel.WARN),
    TASK_REPETITION_INVALID_DURATION(HttpStatus.BAD_REQUEST, ErrorCode.E10004, "반복 시작일은 종료일 이후일 수 없습니다.", LogLevel.WARN),
    NULL_TASK_REPETITION_TYPE(HttpStatus.BAD_REQUEST, ErrorCode.E10005, "반복 타입은 null일 수 없습니다.", LogLevel.WARN),
    NULL_TASK_DAY_OF_MONTH(HttpStatus.BAD_REQUEST, ErrorCode.E10006, "dayOfMonth는 null일 수 업습니다.", LogLevel.WARN),
    NULL_TASK_MONTH_OF_YEAR(HttpStatus.BAD_REQUEST, ErrorCode.E10007, "monthOfYear는 null일 수 업습니다.", LogLevel.WARN),
    TASK_DAY_OF_MONTH_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, ErrorCode.E10008, "dayOfMonth는 1과 31 사이여야 합니다.", LogLevel.WARN),
    TASK_MONTH_OF_YEAR_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, ErrorCode.E10009, "monthOfYear는 1과 12 사이여야 합니다.", LogLevel.WARN),
    NULL_TASK_WEEKDAY_BIT(HttpStatus.BAD_REQUEST, ErrorCode.E10010, "요일 비트는 null일 수 없습니다.", LogLevel.WARN),
    TASK_WEEKDAY_BIT_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, ErrorCode.E10011, "요일 비트는 1 이상 127 이하여야 합니다.", LogLevel.WARN),
    NULL_TASK_REPETITION_WEEK_NUMBER(HttpStatus.BAD_REQUEST, ErrorCode.E10012, "반복 주차는 null일 수 없습니다.", LogLevel.WARN),
    NULL_TASK_REPETITION_WEEKDAY(HttpStatus.BAD_REQUEST, ErrorCode.E10013, "반복 요일은 null일 수 없습니다.", LogLevel.WARN),
    NOT_EQUAL_TASK_DAY_OF_MONTH(HttpStatus.BAD_REQUEST, ErrorCode.E10014, "dayOfMonth와 startDate의 일이 일치하지 않습니다.", LogLevel.WARN),
    NOT_EQUAL_TASK_MONTH_OF_YEAR(HttpStatus.BAD_REQUEST, ErrorCode.E10015, "monthOfYear과 startDate의 월이 일치하지 않습니다.", LogLevel.WARN),
    NOT_EQUAL_TASK_WEEK_NUMBER(HttpStatus.BAD_REQUEST, ErrorCode.E10016, "weekNumber와 startDate의 주차가 일치하지 않습니다.", LogLevel.WARN),
    NOT_EQUAL_TASK_WEEKDAY(HttpStatus.BAD_REQUEST, ErrorCode.E10017, "weekNumber와 startDate의 요일이 일치하지 않습니다.", LogLevel.WARN),

    // TASK_GROUP
    TASK_GROUP_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E11000, "존재하지 않는 할 일 그룹입니다.", LogLevel.WARN),

    // FEED
    NULL_FEED_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E12000, "피드 제목은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_FEED_TITLE(HttpStatus.BAD_REQUEST, ErrorCode.E12001, "피드 제목은 빈 값일 수 없습니다.", LogLevel.WARN),
    INVALID_FEED_TITLE_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E12002, "피드 제목의 길이는 30자를 넘을 수 없습니다.", LogLevel.WARN),
    NULL_FEED_CONTENT(HttpStatus.BAD_REQUEST, ErrorCode.E12003, "피드 내용은 null일 수 없습니다.", LogLevel.WARN),
    INVALID_FEED_CONTENT_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E12004, "피드의 길이는 5000자를 넘을 수 없습니다.", LogLevel.WARN),
    NULL_FEED_IMAGE_URL(HttpStatus.BAD_REQUEST, ErrorCode.E12005, "피드 이미지 url은 null일 수 없습니다.", LogLevel.WARN),
    BLANK_FEED_IMAGE_URL(HttpStatus.BAD_REQUEST, ErrorCode.E12006, "피드 이미지 url은 빈 값일 수 없습니다.", LogLevel.WARN),
    OVER_FEED_IMAGE_COUNT(HttpStatus.BAD_REQUEST, ErrorCode.E12007, "피드 이미지 개수는 10개를 넘을 수 없습니다.", LogLevel.WARN),
    FEED_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E12008, "요청한 피드를 찾을 수 없습니다.", LogLevel.WARN),
    INVALID_FEED_UNLIKE(HttpStatus.BAD_REQUEST, ErrorCode.E12009, "유효하지 않은 피드 좋아요 취소 요청입니다.", LogLevel.WARN),
    NULL_FEED_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, ErrorCode.E12010, "피드 댓글 내용은 null일 수 없습니다.", LogLevel.WARN),
    INVALID_FEED_COMMENT_CONTENT_LENGTH(HttpStatus.BAD_REQUEST, ErrorCode.E12011, "피드 댓글의 길이는 1000자를 넘을 수 없습니다.", LogLevel.WARN),
    FEED_COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E12012, "요청한 피드 댓글을 찾을 수 없습니다.", LogLevel.WARN),
    INVALID_FEED_MODIFY_REQUEST(HttpStatus.BAD_REQUEST, ErrorCode.E12013, "요청한 피드 댓글을 찾을 수 없습니다.", LogLevel.WARN),

    // INVITE
    INVITE_LOG_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E13000, "초대 이력을 찾을 수 없습니다.", LogLevel.WARN),

    // ALARM
    ALARM_LOG_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorCode.E14000, "알림 이력을 찾을 수 없습니다.", LogLevel.WARN),

    // LOCK (E22000)
    FAILED_TO_ACQUIRE_REDIS_LOCK(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "[Redis] Lock 획득에 실패했습니다.", LogLevel.WARN),
    FAILED_TO_ACQUIRE_OPTIMISTIC_LOCK(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "[Optimistic] Lock 획득에 실패했습니다.", LogLevel.WARN),
}
