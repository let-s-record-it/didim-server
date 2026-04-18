package com.didim.api.support.response

data class PageResponse<T>(
    val content: List<T>,
    val isSorted: Boolean,
    val currentPage: Long,
    val size: Long,
    val isFirst: Boolean,
    val isLast: Boolean,
)
