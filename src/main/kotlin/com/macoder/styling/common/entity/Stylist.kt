package com.macoder.styling.common.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne


@Entity
class Stylist(

    @OneToOne
    @JoinColumn(name = "member_id")
    val member: Member,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val stylistId: Long? = null

) {
    var starPoint: Double = 0.0

    var jobProfile: String = ""

    @OneToMany(mappedBy = "stylist", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val strongCategories: MutableList<StrongCategory> = mutableListOf()
}
