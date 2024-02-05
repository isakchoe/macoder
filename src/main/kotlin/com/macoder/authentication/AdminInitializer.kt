package com.macoder.authentication

import com.macoder.authentication.common.MemberType
import com.macoder.authentication.domain.entity.Member
import com.macoder.authentication.persistence.MemberRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AdminInitializer(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        memberRepository.save(Member("admin", encoder.encode("admin"), "관리자", type = MemberType.ADMIN))
    }
}
