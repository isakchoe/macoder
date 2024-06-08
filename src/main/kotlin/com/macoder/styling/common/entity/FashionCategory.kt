package com.macoder.styling.common.entity

import com.macoder.styling.common.enum.Category
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany


@Entity
class FashionCategory(

    val category: Category,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val fashionCategoryId: Long? = null
) {

    // 카테고리별.. 선택한 스타일리스트... 필요한가?
    @OneToMany(mappedBy = "fashionCategory", fetch = FetchType.LAZY)
    val strongCategories:MutableList<StrongCategory> = mutableListOf()

}
