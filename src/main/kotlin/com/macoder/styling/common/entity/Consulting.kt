package com.macoder.styling.common.entity

import com.macoder.styling.consulting.enums.ConsultingStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne


@Entity
class Consulting(

    @ManyToOne
    @JoinColumn(name = "stylist_id")
    val stylist: Stylist,

    @ManyToOne
    @JoinColumn(name = "member_id")
    val member: Member,

    // 컨설팅 진행 상태
    var status: ConsultingStatus =  ConsultingStatus.REQUESTED,

    @OneToOne
    @JoinColumn
    val consultingRequirement: ConsumerRequirement,

    @OneToOne
    @JoinColumn
    val stylistComment: StylistComment? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val consultingId: Long ? = null
) {

    var link: String? = null

    var photoUrl: String? = null



    companion object {
        fun of(stylist: Stylist, member: Member, consultingRequirement: ConsumerRequirement) = Consulting(
            stylist = stylist,
            member = member,
            consultingRequirement = consultingRequirement
        )
    }
}
