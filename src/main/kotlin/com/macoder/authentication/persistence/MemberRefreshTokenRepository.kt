package com.macoder.authentication.persistence

import com.macoder.core.entity.MemberRefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*


interface MemberRefreshTokenRepository : JpaRepository<MemberRefreshToken, Int> {
    fun findByMemberIdAndReissueCountLessThan(id: Int, count: Long): MemberRefreshToken?
}

