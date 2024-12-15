package com.es.jwtSecurityKotlin.exception

class ValidationException (message: String)
    : Exception("Error en la validacion (400). $message")