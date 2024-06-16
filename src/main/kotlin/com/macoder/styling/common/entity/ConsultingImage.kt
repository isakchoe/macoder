package com.macoder.styling.common.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
class ConsultingImage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    val id: Long? = null,
    val contentType: String,
    val path: String,
    val title: String,
    val description: String
)

