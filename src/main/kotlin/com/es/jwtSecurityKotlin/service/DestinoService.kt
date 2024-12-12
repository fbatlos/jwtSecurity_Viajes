package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.model.Destino
import com.es.jwtSecurityKotlin.repository.DestinoRepository
import org.springframework.security.core.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class DestinoService {

    @Autowired
    lateinit var destinoRepository: DestinoRepository

    //TODO Canbiar null por errores personalizados


}