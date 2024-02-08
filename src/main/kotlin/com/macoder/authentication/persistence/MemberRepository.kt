package com.macoder.authentication.persistence

import com.macoder.core.enum.MemberType
import com.macoder.core.entity.Member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Int> {
    fun findByAccount(account: String): Member?
    fun findAllByType(type: MemberType): List<Member>
}
