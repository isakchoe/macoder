package com.macoder.styling.consulting.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ConsultingOrderRequest(
    @Schema(description = "스타일리스트 id", example = "123")
    var stylistId: Int? = null,
    @Schema(description = "회원 id", example = "12")
    val memberId: Int,
    @Schema(description = "문의 사항", example = "가디건 추천 부탁")
    var consultingRequires: String,
)

data class ConsultingWriteRequest(
    @Schema(description = "컨설팅 id", example = "123")
    val consultingId: Int,
    @Schema(description = "컨설팅 컨텐츠", example = "유니클로 추천")
    val consultingContents: String
)
