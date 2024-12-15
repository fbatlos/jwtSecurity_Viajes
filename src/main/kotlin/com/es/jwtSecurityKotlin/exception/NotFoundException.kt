package com.es.jwtSecurityKotlin.exception

class NotFoundException (message: String)
    : Exception("Error no se ha encontrado (404). $message")