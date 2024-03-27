package com.macoder.styling.authentication.persistence

import com.macoder.styling.core.entity.MemberRefreshToken
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRefreshTokenRepository : JpaRepository<MemberRefreshToken, Int> {
    fun findByMemberIdAndReissueCountLessThan(id: Int, count: Long): MemberRefreshToken?
}

