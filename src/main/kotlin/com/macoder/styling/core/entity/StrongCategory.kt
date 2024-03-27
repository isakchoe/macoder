package com.macoder.styling.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class StrongCategory(

    @ManyToOne
    @JoinColumn(name = "category_id")
    val fashionCategory: FashionCategory,

    @ManyToOne()
    @JoinColumn(name = "stylist_id")
    val stylist: Stylist,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val StrongCategoryId: Int? = null

) {




}
