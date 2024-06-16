package com.macoder.styling.consulting.dto

import com.macoder.styling.common.entity.Consulting
import com.macoder.styling.consulting.enums.ConsultingStatus
import io.swagger.v3.oas.annotations.media.Schema


// 소비자가 컨설팅 요청할 때,
data class ConsultingOrderResponse(
    @Schema(description = "스타일리스트 아이디", example = "colabear754")
    var stylistId: Long?,
    @Schema(description = "회원 아이디", example = "1234")
    var memberId: Long?,
    @Schema(description = "컨설팅 아이디", example = "12")
    var consultingId: Long?,
    @Schema(description = "요청 컨설팅", example = "콜라곰")
    var content: String? = null,
) {
    companion object {
        fun from(consulting: Consulting) = ConsultingOrderResponse(
            stylistId = consulting.stylist.stylistId,
            memberId = consulting.member.id,
            consultingId = consulting.consultingId,
            content = consulting.consultingRequirement.detailDescription
        )
    }
}

// 스타일리스트가 컨설팅 작설할 때,
data class ConsultingWriteResponse(
    @Schema(description = "스타일리스트 아이디", example = "colabear754")
    var stylistId: Long?,
    @Schema(description = "회원 아이디", example = "1234")
    var memberId: Long?,
    @Schema(description = "컨설팅 아이디", example = "12")
    var consultingId: Long?,
    @Schema(description = "요청 컨설팅", example = "콜라곰")
    var content: String? = null,
) {
    companion object {
        fun from(consulting: Consulting) = ConsultingWriteResponse(
            stylistId = consulting.stylist.stylistId,
            memberId = consulting.member.id,
            consultingId = consulting.consultingId,
            content = consulting.stylistComment?.consultingContents
        )
    }
}

// todo 하나로 합치기...
data class ConsultingResponse(
    @Schema(description = "컨설팅 아이디", example = "12")
    var consultingId: Long?,

    @Schema(description = "스타일리스트 아이디", example = "colabear754")
    var stylistId: Long?,

    @Schema(description = "회원 아이디", example = "1234")
    var memberId: Long?,

    @Schema(description = "요청 컨설팅", example = "콜라곰")
    var content: String? = null,

    @Schema(description = "진행 상태", example = "요청됨")
    var status: ConsultingStatus? = ConsultingStatus.REQUESTED,

    ) {
    companion object {
        fun from(consulting: Consulting): ConsultingResponse {
            return ConsultingResponse(
                consultingId = consulting.consultingId,
                stylistId = consulting.stylist.stylistId,
                memberId = consulting.member.id,
                content = consulting.consultingRequirement.detailDescription,
                status = consulting.status
            )
        }
    }


}


data class ConsultingListResponse(
    val list: List<ConsultingResponse>
) {
    companion object {
        fun from(consultingList: List<Consulting>) =
            ConsultingListResponse(consultingList.map { x -> ConsultingResponse.from(x) })
    }
}



