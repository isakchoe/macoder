package com.macoder.styling.authentication.service

import com.macoder.styling.authentication.dto.MemberDeleteResponse
import com.macoder.styling.authentication.dto.MemberInfoResponse
import com.macoder.styling.authentication.dto.MemberUpdateRequest
import com.macoder.styling.authentication.dto.MemberUpdateResponse
import com.macoder.styling.authentication.dto.MemberUpdateStylistResponse
import com.macoder.styling.authentication.persistence.MemberRepository
import com.macoder.styling.authentication.persistence.StylistRepository
import com.macoder.styling.util.findByIdOrThrow
import com.macoder.styling.core.entity.Stylist
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val stylistRepository: StylistRepository,
    private val encoder: PasswordEncoder
) {
    @Transactional(readOnly = true)
    fun getMemberInfo(id: Int) = MemberInfoResponse.from(memberRepository.findByIdOrThrow(id, "존재하지 않는 회원입니다."))

    @Transactional
    fun deleteMember(id: Int): MemberDeleteResponse {
        if (!memberRepository.existsById(id)) return MemberDeleteResponse(false)
        memberRepository.deleteById(id)
        return MemberDeleteResponse(true)
    }

    @Transactional
    fun updateMember(id: Int, request: MemberUpdateRequest): MemberUpdateResponse {
        val member = memberRepository.findByIdOrNull(id)?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        member.update(request, encoder)
        return MemberUpdateResponse.of(true, member)
    }

    @Transactional
    fun updateMemberToStylist(id: Int, request: MemberUpdateRequest): MemberUpdateStylistResponse {
        val member = memberRepository.findByIdOrNull(id)?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")

        val stylist = Stylist(member)
        stylistRepository.save(stylist)
        return MemberUpdateStylistResponse.of(true, stylist)
    }
}
