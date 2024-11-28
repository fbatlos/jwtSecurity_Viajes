package com.es.jwtSecurityKotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/secretos_extra_confidenciales")
class SecretosExtraConfidenciales {

    @GetMapping("/ficha1")
    fun getFichaUno () : String {
        return "Es extra secretoooo \uD83E\uDD75"
    }

    @GetMapping("/ficha2")
    fun getFichaDos () : String {
        return "Es extra secretoooo \uD83E\uDD75"
    }
}