package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.Utils.Utils
import com.es.jwtSecurityKotlin.exception.ConflictoBD
import com.es.jwtSecurityKotlin.exception.NotFoundException
import com.es.jwtSecurityKotlin.model.Destino
import com.es.jwtSecurityKotlin.model.Usuario
import com.es.jwtSecurityKotlin.model.Viaje
import com.es.jwtSecurityKotlin.model.ViajeDTO
import com.es.jwtSecurityKotlin.repository.DestinoRepository
import com.es.jwtSecurityKotlin.repository.UsuarioRepository
import com.es.jwtSecurityKotlin.repository.ViajeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ViajeService {

    @Autowired
    private lateinit var viajeRepository: ViajeRepository

    @Autowired
    private lateinit var destinoRepository: DestinoRepository

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    //TODO Canbiar null por errores personalizados

    fun getViajesPorUsuario(authentication: Authentication): List<Viaje?> {
        val myViajes: List<Viaje>

        if (authentication.authorities.any { rol -> rol.authority == "ROLE_ADMIN" }) {
            myViajes = viajeRepository.findAll()
        }else{
            myViajes = viajeRepository.findByUsuarioUsername(authentication.name).orElseThrow{NotFoundException("Viaje not found")}
        }

        return myViajes
    }


    fun postViaje(authentication: Authentication, viajeDTO: ViajeDTO): Viaje {
        val usuario = usuarioRepository.findByUsername(authentication.name)
            .orElseThrow { NotFoundException("Usuario no encontrado") }

        // Verificar si el destino ya está asociado con un viaje del usuario
        val myViajes = viajeRepository.findByUsuarioUsername(authentication.name).orElseThrow{NotFoundException("Viaje not found")}
        myViajes.forEach { myviaje ->
            if (myviaje.destino.idDestino == viajeDTO.destinoId) {
                throw ConflictoBD( "El usuario ya tiene un viaje al mismo destino")
            }
        }

        val destino = destinoRepository.findById(viajeDTO.destinoId)
            .orElseThrow { NotFoundException("Destino no encontrado") }
        
        Utils.validarFechas(viajeDTO.fecha_Ida,viajeDTO.fecha_Regreso)

        // Crear el objeto Viaje y asociarlo con el usuario autenticado
        val viaje = fromDto(viajeDTO, usuario, destino)

        return viajeRepository.save(viaje)
    }


    fun modificarViaje(authentication: Authentication, viajeDTO: ViajeDTO, idViaje:Long): Viaje {
        //Buscamos el viaje para ver si existe
        val viajebd = viajeRepository.findById(idViaje).orElseThrow { NotFoundException("Viaje no encontrado") }

        println(authentication.authorities)
        //Comprobamos que el viaje sea del usuario
        if (!viajebd.usuario?.username.equals(authentication.name)){
            throw AccessDeniedException("No puedes modificar este viaje ya que no es tuyo");
        }

        val destino = destinoRepository.findById(viajeDTO.destinoId)
            .orElseThrow { NotFoundException("Destino no encontrado") }

        if (viajebd.destino != destino) {
            //vemos si ya tiene el destino en alguno de sus viajes
            val myViajes = viajeRepository.findByUsuarioUsername(authentication.name).get()
            myViajes.forEach { myviaje ->
                if (myviaje.destino.idDestino == viajeDTO.destinoId) {
                    throw ConflictoBD("El usuario ya tiene un viaje al mismo destino")
                }
            }
        }

        Utils.validarFechas(viajeDTO.fecha_Ida,viajeDTO.fecha_Regreso)

        viajebd.titulo = viajeDTO.titulo
        viajebd.descripcion = viajeDTO.descripcion
        viajebd.fecha_Ida = viajeDTO.fecha_Ida
        viajebd.fecha_Regreso = viajeDTO.fecha_Regreso
        viajebd.destino = destino

        viajeRepository.save(viajebd)
        return viajebd
    }


    fun eliminarViaje(authentication: Authentication,idViaje:Long): String {

        val viaje = viajeRepository.findById(idViaje)
            .orElseThrow { NotFoundException("Viaje no encontrado") }

        if ( !authentication.authorities.any { it.authority.equals("ROLE_ADMIN") }){
            if (viaje.usuario!!.username != authentication.name) {
                throw AccessDeniedException("No puedes eliminar este viaje")
            }
        }

        viajeRepository.delete(viaje)
        return "El viaje se ha eliminado con exito"
    }




    fun fromDto(dto: ViajeDTO, usuario: Usuario, destino: Destino): Viaje {
        return Viaje(
            titulo = dto.titulo,
            descripcion = dto.descripcion,
            fecha_Ida = dto.fecha_Ida,
            fecha_Regreso = dto.fecha_Regreso,
            usuario = usuario,
            destino = destino
        )
    }

}



