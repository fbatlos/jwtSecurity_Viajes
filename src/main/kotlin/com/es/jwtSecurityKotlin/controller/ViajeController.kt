package com.es.jwtSecurityKotlin.controller

import com.es.jwtSecurityKotlin.model.Viaje
import com.es.jwtSecurityKotlin.model.ViajeDTO
import com.es.jwtSecurityKotlin.service.ViajeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/viajes")
class ViajeController {
    @Autowired
    lateinit var viajeService: ViajeService

    @GetMapping("/mis-viajes")
    fun getViaje(
        authentication: Authentication
    ): ResponseEntity<List<Viaje?>> {

       val myViajes = viajeService.getViajesPorUsuario(authentication)
        return ResponseEntity.ok(myViajes)
    }

    @PostMapping("/viajes")
    fun postViaje(
        authentication: Authentication,
        @RequestBody viaje: ViajeDTO
    ): ResponseEntity<Viaje>{
        val myViaje = viajeService.postViaje(authentication, viaje)
        return ResponseEntity(myViaje, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun putViaje(
        authentication: Authentication,
        @PathVariable id: Long,
        @RequestBody viaje: ViajeDTO
    ):ResponseEntity<Viaje>{
        val viaje = viajeService.modificarViaje(authentication, viaje, id)
        return ResponseEntity<Viaje>(viaje,HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteViaje(
        authentication: Authentication,
        @PathVariable id: Long
    ):ResponseEntity<String>{
        val viaje = viajeService.eliminarViaje(authentication,id)
        return ResponseEntity<String>(viaje, HttpStatus.NO_CONTENT)
    }
}