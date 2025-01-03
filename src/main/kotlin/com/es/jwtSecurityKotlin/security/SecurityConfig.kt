﻿package com.es.jwtSecurityKotlin.security

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain


//Esto es siempre igual
@Configuration
@EnableWebSecurity
class SecurityConfig {



    @Autowired
    private lateinit var rsaKeys: RSAKeysProperties


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .csrf { it.disable() } //cross-site forgery
            .authorizeHttpRequests { auth -> auth
                .requestMatchers(HttpMethod.PUT,"/destinos/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/destinos/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/destinos/destino").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/usuarios/eliminar/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/usuarios/usuarios").hasRole("ADMIN")
                .requestMatchers("/usuarios/register").permitAll()
                .requestMatchers("/usuarios/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/destinos/destinosposibles").permitAll()
                .anyRequest().authenticated()
            }//Los recursos protegidos y publicos
            .oauth2ResourceServer { oauth2 -> oauth2.jwt(Customizer.withDefaults())}
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .httpBasic(Customizer.withDefaults())
            .build()
    }

    @Bean //Los que use spring b
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    /*
    Metodo codificar
     */
    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk =  RSAKey.Builder(rsaKeys.publicKey).privateKey(rsaKeys.privateKey).build()
        val jwks:JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(jwks)
    }


    /*
    Descodificar
     */
    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey).build()
    }


}