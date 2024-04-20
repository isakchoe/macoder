package com.macoder.styling.consulting.dto

import com.macoder.styling.common.entity.Consulting
import com.macoder.styling.common.entity.Member
import com.macoder.styling.common.entity.Stylist
import io.swagger.v3.oas.annotations.media.Schema


data class OrderConsultingResponse(
    @Schema(description = "회원 아이디", example = "colabear754")
    var stylist: Stylist?,
    @Schema(description = "회원 비밀번호", example = "1234")
    val member: Member,
    @Schema(description = "회원 이름", example = "콜라곰")
    var content: String? = null,
) {
    companion object {
        fun from(consulting: Consulting) = OrderConsultingResponse(
            stylist = consulting.stylist,
            member = consulting.member,
            content = consulting.content
        )
    }
}

data class DeliverConsultingResponse(
    @Schema(description = "회원 아이디", example = "colabear754")
    var stylist: Stylist?,
    @Schema(description = "회원 비밀번호", example = "1234")
    val member: Member,
    @Schema(description = "회원 이름", example = "콜라곰")
    var content: String? = null,
) {
    companion object {
        fun from(consulting: Consulting) = DeliverConsultingResponse(
            stylist = consulting.stylist,
            member = consulting.member,
            content = consulting.content
        )
    }
}



