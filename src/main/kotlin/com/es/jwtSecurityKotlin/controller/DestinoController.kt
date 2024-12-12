package com.es.jwtSecurityKotlin.controller

import com.es.jwtSecurityKotlin.model.Destino
import com.es.jwtSecurityKotlin.service.DestinoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/destinos")
class DestinoController {
    @Autowired
    lateinit var destinoService: DestinoService
    //Es un endpoint de ejemplo.

}