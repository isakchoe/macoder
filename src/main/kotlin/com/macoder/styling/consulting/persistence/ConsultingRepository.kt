package com.macoder.styling.consulting.persistence

import com.macoder.styling.common.entity.Consulting
import com.macoder.styling.common.entity.Stylist
import org.springframework.data.jpa.repository.JpaRepository

interface ConsultingRepository: JpaRepository<Consulting, Long> {

    fun findAllByStylist(stylist: Stylist): Consulting

}
