package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.exception.ConflictoBD
import com.es.jwtSecurityKotlin.exception.NotFoundException
import com.es.jwtSecurityKotlin.model.Destino
import com.es.jwtSecurityKotlin.repository.DestinoRepository
import com.es.jwtSecurityKotlin.repository.ViajeRepository
import org.springframework.security.core.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DestinoService {

    @Autowired
    private lateinit var destinoRepository: DestinoRepository

    @Autowired
    private lateinit var viajeRepository: ViajeRepository

    //TODO Canbiar null por errores personalizados

    fun getDestinos(authentication: Authentication): List<Destino> {
        return  destinoRepository.findAll()
    }

    fun postDestino(destino: Destino): Destino {

        val destinos = destinoRepository.findAll()

        if (destinos.any { it.nombre.uppercase().equals(destino.nombre.uppercase()) }) {
            throw ConflictoBD("El destino ${destino.nombre} existente")
        }

        destinoRepository.save(destino)
        return destino
    }

    fun putDestino(destino: Destino , idDestino:Long): Destino {
        val destinobd = destinoRepository.findById(idDestino)
            .orElseThrow { NotFoundException("No se ha encontrado el destino a actualizar.") }
        //No puedo poner 2 destinos iguales
        val destinos = destinoRepository.findAll()

        if (destinos.any { it.nombre.uppercase().equals(destino.nombre.uppercase()) }) {
            throw ConflictoBD("El destino ${destino.nombre} existente")
        }

        destinobd.origen = destino.origen
        destinobd.pais = destino.pais
        destinobd.nombre = destino.nombre
        destinobd.descripcion = destino.descripcion

        destinoRepository.save(destinobd)

        return destinobd
    }

    fun deleteDestino(idDestino: Long): String{

        val destino = destinoRepository.findById(idDestino).orElseThrow { NotFoundException("No se ha encontrado el destino a eliminar.") }

        //Hay que mirar si existe en la tabla de viajes, sino existe se puede eliminar con tranquilidad.
        if ( viajeRepository.findAll().any { it.destino.idDestino?.equals(destino.idDestino) == true }) {
            throw ConflictoBD("El destino no puede ser eliminado ya que está asociado a 1 o más viajes.")
        }

        destinoRepository.delete(destino)
        return "Se ha eliminado correctamente el destino."
    }


}