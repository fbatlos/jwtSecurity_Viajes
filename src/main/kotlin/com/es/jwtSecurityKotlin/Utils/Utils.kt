package com.es.jwtSecurityKotlin.Utils

object Utils {

    fun validarTelefono(telefono: String): Boolean{
        val regex = Regex("^\\+\\d{1,3}[\\s-]?\\d{1,4}[\\s-]?\\d{1,4}[\\s-]?\\d{1,4}$")
        return regex.matches(telefono)
    }


}