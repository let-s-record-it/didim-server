package com.didim.common.pagination

data class Pageable(
    val page: Long,
    val size: Long,
) {
    val offset: Long get() = page * size
    val limit: Long get() = size
}