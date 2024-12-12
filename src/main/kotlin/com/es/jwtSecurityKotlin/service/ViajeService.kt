package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.model.Destino
import com.es.jwtSecurityKotlin.model.Viaje
import com.es.jwtSecurityKotlin.repository.UsuarioRepository
import com.es.jwtSecurityKotlin.repository.ViajeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service


@Service
class ViajeService {

    @Autowired
    lateinit var viajeRepository: ViajeRepository

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    //TODO Canbiar null por errores personalizados

    fun getDestino(authentication: Authentication): List<Viaje?> {
        val usuario = usuarioRepository.findByUsername(authentication.name).get()

        val destinos = viajeRepository.findByUsuario(usuario).get()
        return destinos
    }
}