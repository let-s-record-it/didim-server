package com.didim.common.pagination

data class Page<T>(
    val content: List<T>,
    val pageable: Pageable,
    val hasNext: Boolean,
) {
    val isFirst: Boolean = pageable.page == 0L
    val isLast: Boolean = !hasNext

    fun <R> map(transform: (T) -> R): Page<R> {
        return Page(content.map(transform), pageable, hasNext)
    }
}