package com.macoder.styling.common.entity

import com.macoder.styling.consulting.enums.ConsultingStatus
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany


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

    @OneToMany(mappedBy = "consulting", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val consumerRequirements: MutableList<ConsumerRequirement> = mutableListOf(),

    @OneToMany(mappedBy = "consulting", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val stylistComments: MutableList<StylistComment> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val consultingId: Long ? = null
) {
    var link: String? = null
    var photoUrl: String? = null

    companion object {
        fun of(stylist: Stylist, member: Member) = Consulting(
            stylist = stylist,
            member = member
        )
    }
}
