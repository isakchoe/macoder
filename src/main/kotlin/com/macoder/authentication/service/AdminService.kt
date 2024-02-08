package com.macoder.authentication.service

import com.macoder.core.enum.MemberType
import com.macoder.authentication.domain.dto.MemberInfoResponse
import com.macoder.authentication.persistence.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(private val memberRepository: MemberRepository) {
    @Transactional(readOnly = true)
    fun getMembers(): List<MemberInfoResponse> = memberRepository.findAllByType(MemberType.USER).map(MemberInfoResponse::from)

    @Transactional(readOnly = true)
    fun getAdmins(): List<MemberInfoResponse> = memberRepository.findAllByType(MemberType.ADMIN).map(MemberInfoResponse::from)
}
