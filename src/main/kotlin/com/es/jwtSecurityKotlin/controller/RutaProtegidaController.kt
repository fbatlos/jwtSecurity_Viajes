package com.es.jwtSecurityKotlin.controller

import com.es.jwtSecurityKotlin.model.Usuario
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

}