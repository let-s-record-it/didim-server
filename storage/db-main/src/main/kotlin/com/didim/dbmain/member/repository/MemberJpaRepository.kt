package com.didim.dbmain.member.repository

import com.didim.dbmain.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {
}