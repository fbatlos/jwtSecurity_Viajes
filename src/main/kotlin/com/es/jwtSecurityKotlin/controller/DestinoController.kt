package com.es.jwtSecurityKotlin.controller

import com.es.jwtSecurityKotlin.model.Destino
import com.es.jwtSecurityKotlin.service.DestinoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/destinos")
class DestinoController {
    @Autowired
    lateinit var destinoService: DestinoService
    //Es un endpoint de ejemplo.

    @GetMapping("/destinosposibles")
    fun destinosPosibles(
        authentication: Authentication
    ): ResponseEntity<List<Destino>> {
        val destinos =  destinoService.getDestinos(authentication)
        return ResponseEntity(destinos, HttpStatus.OK)
    }

    @PostMapping("/destino")
    fun postDestino(
        authentication: Authentication,
        @RequestBody destino: Destino
    ): ResponseEntity<Destino> {

        destinoService.postDestino(destino)
        return ResponseEntity(destino, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun putDestino(
        authentication: Authentication,
        @RequestBody destino: Destino,
        @PathVariable id: Long
    ): ResponseEntity<Destino> {
        val destino = destinoService.putDestino(destino, id)
        return ResponseEntity(destino, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteDestino(
        authentication: Authentication,
        @PathVariable id: Long
    ): ResponseEntity<String> {
        val eliminacion = destinoService.deleteDestino(id)
        return ResponseEntity(eliminacion, HttpStatus.OK)
    }


}