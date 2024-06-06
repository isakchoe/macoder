package com.macoder.styling.common.entity

import com.macoder.styling.common.enum.MemberType
import com.macoder.styling.authentication.dto.MemberUpdateRequest
import com.macoder.styling.authentication.dto.SignUpRequest
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@Entity
class Member(

    @Column(nullable = false, scale = 20, unique = true)
    val account: String,

    @Column(nullable = false)
    var password: String,

    var name: String? = null,

    var age: Int? = null,

    @Enumerated(EnumType.STRING)
    var type: MemberType = MemberType.USER,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

) {
    val createdAt: LocalDateTime = LocalDateTime.now()

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    val stylist: Stylist?= null

    companion object {
        fun from(request: SignUpRequest, encoder: PasswordEncoder) = Member(
            account = request.account,
            password = encoder.encode(request.password),
            name = request.name,
            age = request.age
        )
    }


    fun becomeStylist() {
        this.type = MemberType.STYLIST
    }


    fun update(newMember: MemberUpdateRequest, encoder: PasswordEncoder) {
        this.password = newMember.newPassword
            ?.takeIf { it.isNotBlank() }
            ?.let { encoder.encode(it) }
            ?: this.password
        this.name = newMember.name
        this.age = newMember.age
    }
}
