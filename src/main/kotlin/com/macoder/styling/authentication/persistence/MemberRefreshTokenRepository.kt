package com.macoder.styling.authentication.persistence

import com.macoder.styling.common.entity.MemberRefreshToken
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRefreshTokenRepository : JpaRepository<MemberRefreshToken, Long> {
    fun findByMemberIdAndReissueCountLessThan(id: Long, count: Long): MemberRefreshToken?
}

