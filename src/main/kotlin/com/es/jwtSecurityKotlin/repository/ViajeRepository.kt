package com.es.jwtSecurityKotlin.repository

import com.es.jwtSecurityKotlin.model.Usuario
import com.es.jwtSecurityKotlin.model.Viaje
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.List

@Repository
interface ViajeRepository: JpaRepository<Viaje, Long> {
     fun findByUsuarioUsername(username:String): Optional<List<Viaje>>
}