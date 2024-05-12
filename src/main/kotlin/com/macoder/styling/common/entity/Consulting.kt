package com.macoder.styling.common.entity

import jakarta.persistence.Entity
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

    var consultingRequire: String,

    var consultingContents: String? = null,

    @Id
    val consultingId: Int ? = null
) {
    companion object {
        fun from(stylist: Stylist, member: Member, content: String) = Consulting(
            stylist = stylist,
            member = member,
            consultingRequire = content
        )
    }
}
