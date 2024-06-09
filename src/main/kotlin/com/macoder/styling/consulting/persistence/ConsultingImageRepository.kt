package com.macoder.styling.consulting.persistence

import com.macoder.styling.common.entity.ConsultingImage
import org.springframework.data.jpa.repository.JpaRepository

interface ConsultingImageRepository: JpaRepository<ConsultingImage, Long> {
}
