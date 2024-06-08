package com.macoder.styling.authentication.persistence

import com.macoder.styling.common.entity.Stylist
import org.springframework.data.jpa.repository.JpaRepository


interface StylistRepository: JpaRepository<Stylist, Long> {


}
