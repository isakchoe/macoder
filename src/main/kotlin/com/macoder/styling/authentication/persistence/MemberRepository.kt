package com.macoder.styling.authentication.persistence

import com.macoder.styling.core.enum.MemberType
import com.macoder.styling.core.entity.Member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Int> {
    fun findByAccount(account: String): Member?
    fun findAllByType(type: MemberType): List<Member>
}
