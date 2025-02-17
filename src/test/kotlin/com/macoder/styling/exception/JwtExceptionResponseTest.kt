package com.macoder.styling.exception

import com.macoder.styling.common.enum.ApiStatus
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

private val request get() = get("/admin/members").contentType(MediaType.APPLICATION_JSON)
private const val EXPIRED_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYTQwYWI4NS01NGVmLTQzZTYtYmJmOS1kMzljYzI5Y2Q5MWY6QURNSU4iLCJpc3MiOiJjb2xhYmVhcjc1NCIsImlhdCI6MTY4NTAyNDI2MywiZXhwIjoxNjg1MDI0MjY2fQ.FoDD9P-YXqAFebYDHcHKLQhH3UZgZnoPkcoXuShQDEqhQ_vAuq80MWHsRvvbvuBbms_Q3SweUNPDFFPtNZTR8w"
private const val TAMPERED_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmMGY3YmZkNS0zYmMxLTRmYTctYTkzMi03NjMyNDRjMzkzYjk6QURNSU4iLCJpc3MiOiJjb2xhYmVhcjc1NCIsImlhdCI6MTY4NTAyNDMxM30.f0Puq1FE-68kvjGnejs_wjKdWdfQLWiHqHQBWi1INUc"
private const val MALFORMED_TOKEN = "1234567890"

@SpringBootTest
@AutoConfigureMockMvc
class JwtExceptionResponseTest @Autowired constructor(
    private val mock: MockMvc
) {
    @Test
    fun `토큰이 없는 경우`() {
        // given
        // when
        val actions = mock.perform(request)
        // then
        actions.andExpect(status().isForbidden)
            .andExpect(jsonPath("$.status").value(ApiStatus.ERROR.name))
            .andExpect(jsonPath("$.message").value("접근이 거부되었습니다."))
            .andExpect(jsonPath("$.data").isEmpty)
    }

    @Test
    fun `토큰이 만료된 경우`() {
        // given
        // when
        val actions = mock.perform(request.header(HttpHeaders.AUTHORIZATION, "Bearer $EXPIRED_TOKEN"))
        // then
        actions.andExpect(status().isUnauthorized)
            .andExpect(jsonPath("$.status").value(ApiStatus.ERROR.name))
            .andExpect(jsonPath("$.message").value("토큰이 만료되었습니다. 다시 로그인해주세요."))
            .andExpect(jsonPath("$.data").isEmpty)
    }

    @Test
    fun `토큰이 변조된 경우`() {
        // given
        // when
        val actions = mock.perform(request.header(HttpHeaders.AUTHORIZATION, "Bearer $TAMPERED_TOKEN"))
        // then
        actions.andExpect(status().isUnauthorized)
            .andExpect(jsonPath("$.status").value(ApiStatus.ERROR.name))
            .andExpect(jsonPath("$.message").value("토큰이 유효하지 않습니다."))
            .andExpect(jsonPath("$.data").isEmpty)
    }

    @Test
    fun `잘못된 토큰인 경우`() {
        // given
        // when
        val actions = mock.perform(request.header(HttpHeaders.AUTHORIZATION, "Bearer $MALFORMED_TOKEN"))
        // then
        actions.andExpect(status().isUnauthorized)
            .andExpect(jsonPath("$.status").value(ApiStatus.ERROR.name))
            .andExpect(jsonPath("$.message").value("올바르지 않은 토큰입니다."))
            .andExpect(jsonPath("$.data").isEmpty)
    }
}
