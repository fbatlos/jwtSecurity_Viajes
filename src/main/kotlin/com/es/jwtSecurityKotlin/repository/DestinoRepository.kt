package com.es.jwtSecurityKotlin.repository

import com.es.jwtSecurityKotlin.model.Destino
import org.springframework.data.jpa.repository.JpaRepository

interface DestinoRepository : JpaRepository<Destino, Long> {
}