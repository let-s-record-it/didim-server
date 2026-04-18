package com.didim.domain.support

interface JsonSerializer {
    fun <T> serialize(value: T): String
}
