package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.model.Destino
import com.es.jwtSecurityKotlin.repository.DestinoRepository
import com.example.unsecuredseguros.exception.ConflictoBD
import com.example.unsecuredseguros.exception.NotFoundException
import org.springframework.security.core.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class DestinoService {

    @Autowired
    private lateinit var destinoRepository: DestinoRepository

    //TODO Canbiar null por errores personalizados

    fun getDestinos(authentication: Authentication): List<Destino> {
        return  destinoRepository.findAll()
    }

    fun postDestino(destino: Destino): Destino {

        val destinos = destinoRepository.findAll()

        if (destinos.any { it.origen.equals(destino.origen) }) {
            throw ConflictoBD("El destino ${destino.origen} existente")
        }

        destinoRepository.save(destino)
        return destino
    }

    fun putDestino(destino: Destino , idDestino:Long): Destino {
        val destinobd = destinoRepository.findById(idDestino)
            .orElseThrow { NotFoundException("No se ha encontrado el destino a actualizar.")}

        destinobd.origen = destino.origen
        destinobd.pais = destino.pais
        destinobd.nombre = destino.nombre
        destinobd.descripcion = destino.descripcion

        destinoRepository.save(destinobd)

        return destinobd
    }

    fun deleteDestino(idDestino: Long): String{

        val destino = destinoRepository.findById(idDestino).orElseThrow { NotFoundException("No se ha encontrado el destino a eliminar.") }
        destinoRepository.delete(destino)
        return "Se ha eliminado correctamente el destino."
    }


}