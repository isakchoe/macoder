package com.macoder.styling.consulting.persistence

import com.macoder.styling.common.entity.Consulting
import org.springframework.data.jpa.repository.JpaRepository

interface ConsultingRepository: JpaRepository<Consulting, Int> {

}
