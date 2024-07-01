package com.macoder.styling.common.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne


@Entity
class StylistComment(

    @ManyToOne
    @JoinColumn(name = "stylist_id")
    val stylist: Stylist,

    @ManyToOne
    @JoinColumn(name = "first_image_id")
    val consultingImage1: ConsultingImage? = null,

    @ManyToOne
    @JoinColumn(name = "sec_image_id")
    val consultingImage2: ConsultingImage? = null,

    @ManyToOne
    @JoinColumn(name = "consulting_id")
    val consulting: Consulting,

    var consultingContents: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    companion object {
        fun of(consulting: Consulting, consultingContents: String?, stylist: Stylist, consultingImage1: ConsultingImage?, consultingImage2: ConsultingImage?): StylistComment {
            return StylistComment(
                consulting = consulting,
                consultingContents = consultingContents,
                consultingImage1 = consultingImage1,
                consultingImage2 = consultingImage2,
                stylist = stylist
            )
        }
    }
}
