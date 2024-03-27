package com.macoder.authentication.persistence

import com.macoder.core.entity.Stylist
import org.springframework.data.jpa.repository.JpaRepository


interface StylistRepository: JpaRepository<Stylist, Int> {


}
