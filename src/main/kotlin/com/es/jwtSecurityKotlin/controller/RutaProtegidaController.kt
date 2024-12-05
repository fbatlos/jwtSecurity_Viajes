package com.es.jwtSecurityKotlin.controller

import com.es.jwtSecurityKotlin.model.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rutas_protegidas")
class RutaProtegidaController {


    @GetMapping("/recurso1")
    fun getRecursoProtegidoUno () : String {
        return "Este recurso sólo puede ser accedido por usuarios registrados en la BDD \uD83E\uDD75"
    }

    @GetMapping("/recurso1/{id}")
    fun getRecursoProtegidoUnoPorID (
        @PathVariable("id") id: String
    ) : String {
        return "Seguro por ID : $id \uD83E\uDD75"
    }

    @DeleteMapping("/recurso1/{id}")
    fun deleteRecursoProtegidoUnoPorID (
        @PathVariable("id") id: String
    ) : String {
        return "Eliminar seguro por ID : $id \uD83E\uDD75"
    }

    @PostMapping("/recurso2")
    fun postRecursoProtegido():String{
        return "Hola has insertado un recurso \uD83E\uDD75 \uD83E\uDD75 \uD83E\uDD75 \uD83E\uDD75 ."
    }

    @GetMapping("/usuario_autenticado")
    fun saludarUsuarioAutenticado(authentication: Authentication): String{
        return "Hola : ${authentication.name}"
    }



    @DeleteMapping("/eliminar/{nombre}")
    fun eliminarPorNombre(
        authentication: Authentication,
        @PathVariable("nombre") nombre: String
    ): String{
        if (authentication.name == nombre){
            return "Eliminar seguridad, te vas a eliminar a ti cateto."
        }

        if (authentication.authorities.any{rol
                -> rol.authority == "ROLE_ADMIN"}){
            return "Has eliminado $nombre."
        }
        return "No puedes pasar ${authentication.name}"
    }
}