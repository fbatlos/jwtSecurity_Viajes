package com.es.jwtSecurityKotlin.model

import java.time.LocalDate

data class ViajeDTO(
    val titulo: String,
    val descripcion: String?,
    val fecha_Ida: LocalDate,
    val fecha_Regreso: LocalDate,
    val destinoId: Long  // Solo el ID del destino
)
