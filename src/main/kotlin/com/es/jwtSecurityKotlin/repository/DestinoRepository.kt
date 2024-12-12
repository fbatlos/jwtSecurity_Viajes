package com.es.jwtSecurityKotlin.repository

import com.es.jwtSecurityKotlin.model.Destino
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DestinoRepository : JpaRepository<Destino, Long> {

}