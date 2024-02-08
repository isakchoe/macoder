package com.macoder.authentication.service

import com.macoder.core.enum.MemberType
import com.macoder.authentication.domain.dto.SignInRequest
import com.macoder.authentication.domain.dto.SignUpRequest
import com.macoder.core.entity.Member
import com.macoder.authentication.persistence.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
class SignServiceTest @Autowired constructor(
    private val signService: SignService,
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder
) {
    @BeforeEach
    @AfterEach
    fun clear() {
        memberRepository.deleteAll()
    }

    @Test
    fun 회원가입() {
        // given
        val request = SignUpRequest("colabear754", "1234", "콜라곰", 27)
        // when
        val response = signService.registMember(request)
        // then
        assertThat(response.account).isEqualTo("colabear754")
        assertThat(response.name).isEqualTo("콜라곰")
        assertThat(response.age).isEqualTo(27)
    }

    @Test
    fun `아이디는 중복될 수 없다`() {
        // given
        memberRepository.save(Member("colabear754", "1234"))
        val request = SignUpRequest("colabear754", "1234")
        // when
        // then
        assertThrows(IllegalArgumentException::class.java) {
            signService.registMember(request)
        }.also { assertThat(it.message).isEqualTo("이미 사용중인 아이디입니다.") }
    }

    @Test
    fun 로그인() {
        // given
        memberRepository.save(Member("colabear754", encoder.encode("1234"), "콜라곰"))
        // when
        val response = signService.signIn(SignInRequest("colabear754", "1234"))
        // then
        assertThat(response.name).isEqualTo("콜라곰")
        assertThat(response.type).isEqualTo(MemberType.USER)
    }

    @Test
    fun 로그인실패() {
        // given
        memberRepository.save(Member("colabear754", "1234", "콜라곰"))
        // when
        // then
        assertThrows(IllegalArgumentException::class.java) {
            signService.signIn(SignInRequest("colabear754", "4321"))
        }.also { assertThat(it.message).isEqualTo("아이디 또는 비밀번호가 일치하지 않습니다.") }
    }
}
