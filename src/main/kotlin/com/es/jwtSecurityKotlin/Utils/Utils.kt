package com.es.jwtSecurityKotlin.Utils

import com.es.jwtSecurityKotlin.exception.ValidationException
import java.time.LocalDate

object Utils {

    fun validarTelefono(telefono: String): Boolean{
        val regex = Regex("^\\+\\d{1,3}[\\s-]?\\d{1,4}[\\s-]?\\d{1,4}[\\s-]?\\d{1,4}$")
        return regex.matches(telefono)
    }

    fun validarFechas(fechaIda: LocalDate, fechaRegreso: LocalDate) {

        if (fechaIda.isAfter(fechaRegreso)) {
            throw ValidationException("La fecha de ida no puede ser posterior a la fecha de regreso.")
        }
    }


}