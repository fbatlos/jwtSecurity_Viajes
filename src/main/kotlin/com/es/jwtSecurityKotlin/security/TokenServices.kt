package com.es.jwtSecurityKotlin.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.Date


@Service
class TokenServices {

    @Autowired
    private lateinit var jwtEncoder : JwtEncoder


    fun generarToken(autentication: Authentication): String {

        //Contiene los roles
        val roles:String = autentication.authorities.map {it.authority}.joinToString(" ")

        val payload: JwtClaimsSet = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(1 , ChronoUnit.HOURS))
            .subject(autentication.name)
            .claim("roles", roles)
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(payload)).getTokenValue()
    }
}