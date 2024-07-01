package com.macoder.styling.common.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne


@Entity
class ConsumerRequirement(
    @ManyToOne
    @JoinColumn(name = "member_id")
    val consumer: Member,

    @ManyToOne
    @JoinColumn(name = "first_image_id")
    val consultingImage1: ConsultingImage? = null,

    @ManyToOne
    @JoinColumn(name = "sec_image_id")
    val consultingImage2: ConsultingImage? = null,

    @ManyToOne
    @JoinColumn(name = "consulting_id")
    val consulting: Consulting,

    var detailDescription: String? = null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    companion object {

        fun of(consulting: Consulting, member: Member, image1: ConsultingImage?, image2: ConsultingImage?, detailDescription: String?): ConsumerRequirement {
            return ConsumerRequirement(
                consulting = consulting,
                consumer = member,
                consultingImage1 = image1,
                consultingImage2 = image2,
                detailDescription = detailDescription
            )
        }
    }
}
