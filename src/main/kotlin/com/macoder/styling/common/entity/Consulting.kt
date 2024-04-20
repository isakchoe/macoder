package com.macoder.styling.common.entity

import com.macoder.styling.consulting.dto.OrderConsultingRequest
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne


@Entity
class Consulting(

    @ManyToOne
    @JoinColumn(name = "stylist_id")
    var stylist: Stylist?,

    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member,

    var content: String?,

    @Id
    val consultingId: Int ? = null
) {
    companion object {
        fun from(request: OrderConsultingRequest) = Consulting(
            stylist = request.stylist,
            member = request.member,
            content = request.content
        )
    }
}
