package com.didim.api.support.util

import com.didim.common.domain.ImageData
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import org.springframework.web.multipart.MultipartFile
import java.util.*

private val IMAGE_CONTENT_TYPES =
    listOf(
        "image/jpeg",
        "image/png",
        "image/gif",
        "image/webp",
        "image/svg+xml",
    )

fun generateUUIDFileName(fileName: String) = "${UUID.randomUUID()}_${fileName.replace("\\s".toRegex(), "_")}"

fun validateImageContentType(contentType: String?): String {
    if (contentType !in IMAGE_CONTENT_TYPES) {
        throw AppException(ErrorType.INVALID_IMAGE_TYPE)
    }

    return contentType!!
}

fun MultipartFile.toImageData(): ImageData {
    val originFileName = this.originalFilename ?: throw AppException(ErrorType.INVALID_IMAGE_FILE_NAME)

    return ImageData(
        data = this.inputStream,
        contentType = validateImageContentType(this.contentType),
        originalFilename = originFileName,
        fileName = generateUUIDFileName(originFileName),
    )
}