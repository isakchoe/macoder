package com.macoder.styling.common.entity

import com.macoder.styling.consulting.enums.ConsultingStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne


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

    // 소비자가 작성한 요구 사항
    var consultingRequire: String? = null,

    // 스타일리스트 답변
    var consultingContents: String? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val consultingId: Long ? = null
) {

    var link: String? = null

    var photoUrl: String? = null


    fun writeConsultingContents(content: String){
        this.consultingContents = content
    }

    companion object {
        fun of(stylist: Stylist, member: Member, content: String) = Consulting(
            stylist = stylist,
            member = member,
            consultingRequire = content
        )
    }
}
