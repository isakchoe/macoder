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

    // image_id 2ㄱㅐ??
    // null 허용해야함.. manytoone 아마 not null 일듯 ...
    @ManyToOne
    @JoinColumn(name = "first_image_id")
    val consultingImage1: ConsultingImage? = null,

    @ManyToOne
    @JoinColumn(name = "sec_image_id")
    val consultingImage2: ConsultingImage? = null,

    var detailDescription: String? = null

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    companion object {

        fun of(member: Member, image1: ConsultingImage?, image2: ConsultingImage?, detailDescription: String?): ConsumerRequirement {
            return ConsumerRequirement(
                consumer = member,
                consultingImage1 = image1,
                consultingImage2 = image2,
                detailDescription = detailDescription
            )
        }
    }
}
