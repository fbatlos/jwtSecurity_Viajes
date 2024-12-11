package com.es.jwtSecurityKotlin.repository

import com.es.jwtSecurityKotlin.model.Viaje
import org.springframework.data.jpa.repository.JpaRepository

interface ViajeRepository: JpaRepository<Viaje, Long> {
}