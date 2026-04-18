package com.didim.api.member.dto.request

import com.didim.domain.member.domain.EditMember
import jakarta.validation.constraints.Size

data class ProfileModifyRequest(
    @field:Size(max = 10)
    val name: String,
    @field:Size(max = 20)
    val job: String,
) {
    fun toEditMember(memberKey: String) = EditMember(
        name = name,
        job = job,
        memberKey = memberKey,
    )
}
