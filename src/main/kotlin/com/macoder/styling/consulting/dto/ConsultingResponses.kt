package com.macoder.styling.consulting.dto

import com.macoder.styling.common.entity.Consulting
import io.swagger.v3.oas.annotations.media.Schema


// 소비자가 컨설팅 요청할 때,
data class ConsultingOrderResponse(
    @Schema(description = "스타일리스트 아이디", example = "colabear754")
    var stylistId: Int?,
    @Schema(description = "회원 아이디", example = "1234")
    var memberId: Int?,
    @Schema(description = "컨설팅 아이디", example = "12")
    var consultingId: Int?,
    @Schema(description = "요청 컨설팅", example = "콜라곰")
    var content: String? = null,
) {
    companion object {
        fun from(consulting: Consulting) = ConsultingOrderResponse(
            stylistId = consulting.stylist.stylistId,
            memberId = consulting.member.id,
            consultingId = consulting.consultingId,
            content = consulting.consultingRequire
        )
    }
}

// 스타일리스트가 컨설팅 작설할 때,
data class ConsultingWriteResponse(
    @Schema(description = "스타일리스트 아이디", example = "colabear754")
    var stylistId: Int?,
    @Schema(description = "회원 아이디", example = "1234")
    var memberId: Int?,
    @Schema(description = "컨설팅 아이디", example = "12")
    var consultingId: Int?,
    @Schema(description = "요청 컨설팅", example = "콜라곰")
    var content: String? = null,
) {
    companion object {
        fun from(consulting: Consulting) = ConsultingWriteResponse(
            stylistId = consulting.stylist.stylistId,
            memberId = consulting.member.id,
            consultingId = consulting.consultingId,
            content = consulting.consultingContents
        )
    }
}



