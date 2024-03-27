package com.macoder.authentication.service

import com.macoder.authentication.domain.dto.SignInRequest
import com.macoder.authentication.domain.dto.SignInResponse
import com.macoder.authentication.domain.dto.SignUpRequest
import com.macoder.authentication.domain.dto.SignUpResponse
import com.macoder.authentication.persistence.MemberRefreshTokenRepository
import com.macoder.core.entity.Member
import com.macoder.authentication.persistence.MemberRepository
import com.macoder.authentication.security.TokenProvider
import com.macoder.authentication.util.flushOrThrow
import com.macoder.core.entity.MemberRefreshToken
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignService(
    private val memberRepository: MemberRepository,
    private val memberRefreshTokenRepository: MemberRefreshTokenRepository,
    private val tokenProvider: TokenProvider,
    private val encoder: PasswordEncoder
) {
    @Transactional
    fun registMember(request: SignUpRequest) = SignUpResponse.from(
        memberRepository.flushOrThrow(IllegalArgumentException("이미 사용중인 아이디입니다.")) { save(Member.from(request, encoder)) }
    )

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        val member = memberRepository.findByAccount(request.account)
            ?.takeIf { encoder.matches(request.password, it.password) } ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        val accessToken = tokenProvider.createAccessToken("${member.id}:${member.type}")
        val refreshToken = tokenProvider.createRefreshToken()
        memberRefreshTokenRepository.findByIdOrNull(member.id)?.updateRefreshToken(refreshToken)
            ?: memberRefreshTokenRepository.save(MemberRefreshToken(member, refreshToken))
        return SignInResponse(member.name, member.type, accessToken, refreshToken)
    }


}
