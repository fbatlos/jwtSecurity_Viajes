package com.es.jwtSecurityKotlin.controller

import com.es.jwtSecurityKotlin.model.Destino
import com.es.jwtSecurityKotlin.model.Viaje
import com.es.jwtSecurityKotlin.service.ViajeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/viajes")
class ViajeController {
    @Autowired
    lateinit var viajeService: ViajeService

    @GetMapping("/viaje")
    fun getDestino(
        authentication: Authentication
    ): ResponseEntity<List<Viaje?>> {

       val destino = viajeService.getDestino(authentication)
        return ResponseEntity.ok(destino)
    }
}