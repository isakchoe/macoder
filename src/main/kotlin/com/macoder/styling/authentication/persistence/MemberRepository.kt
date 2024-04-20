package com.macoder.styling.authentication.persistence

import com.macoder.styling.common.enum.MemberType
import com.macoder.styling.common.entity.Member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Int> {
    fun findByAccount(account: String): Member?
    fun findAllByType(type: MemberType): List<Member>
}
