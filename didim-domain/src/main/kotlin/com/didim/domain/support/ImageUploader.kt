package com.didim.domain.support

import com.didim.common.domain.ImageData

interface ImageUploader {
    fun upload(image: ImageData): String
    fun delete(path: String)
}