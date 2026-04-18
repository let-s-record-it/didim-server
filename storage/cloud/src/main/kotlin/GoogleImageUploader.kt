package com.didim.storage

import com.didim.common.domain.ImageData
import com.didim.common.exception.AppException
import com.didim.common.exception.ErrorType
import com.didim.domain.support.ImageUploader
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

private const val GCS_HOST = "https://storage.googleapis.com/"

@Component
class GoogleImageUploader(
    private val storage: Storage,
    @field:Value($$"${spring.cloud.gcp.storage.bucket}")
    private val bucket: String,
) : ImageUploader {

    override fun upload(image: ImageData): String {
        val imageFileName = image.fileName

        return try {
            storage.createFrom(
                BlobInfo
                    .newBuilder(bucket, imageFileName)
                    .setContentType(image.contentType)
                    .build(),
                image.data,
            )

            "$GCS_HOST$bucket/$imageFileName"
        } catch (e: Exception) {
            throw AppException(ErrorType.IMAGE_UPLOAD_FAILED, e.message)
        }
    }

    override fun delete(path: String) {
        val blobName = path.removePrefix("$GCS_HOST$bucket/")
        storage.delete(bucket, blobName)
    }
}
