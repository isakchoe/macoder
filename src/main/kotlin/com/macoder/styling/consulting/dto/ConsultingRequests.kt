package com.macoder.styling.consulting.dto

import com.macoder.styling.common.entity.Member
import com.macoder.styling.common.entity.Stylist
import io.swagger.v3.oas.annotations.media.Schema

data class OrderConsultingRequest(
    @Schema(description = "스타일리스트", example = "부삭 스타일리스트")
    var stylist : Stylist? = null,
    @Schema(description = "회원", example = "존")
    val member: Member,
    @Schema(description = "문의 사항", example = "가디건 추천 부탁")
    var content: String? = null,
)

data class DeliverConsultingRequest(
    @Schema(description = "회원 아이디", example = "colabear754")
    val account: String,
    @Schema(description = "회원 비밀번호", example = "1234")
    val password: String
)
