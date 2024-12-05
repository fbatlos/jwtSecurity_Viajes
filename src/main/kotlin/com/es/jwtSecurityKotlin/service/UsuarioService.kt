package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.model.Usuario
import com.es.jwtSecurityKotlin.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsuarioService : UserDetailsService {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository


    /*
    TODO VIP
     */
    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario:Usuario = usuarioRepository
            .findByUsername(username!!)
            .orElseThrow()


        return User.builder()
            .username(usuario.username)
            .password(usuario.password)
            .roles(usuario.roles)
            .build()
    }


    /*
    MÉTODO PARA INSERTAR UN USUARIO
     */
    fun registerUsuario(usuario: Usuario) : Usuario? {

        // Comprobamos que el usuario no existe en la base de datos
        usuario.username ?:  throw ResponseStatusException(HttpStatus.FOUND)
        if(usuarioRepository.findByUsername(usuario.username!!).isPresent) {
            throw ResponseStatusException(HttpStatus.FOUND)
        }

        // Creamos la instancia de Usuario

        val newUser:Usuario = usuario

        /*
         La password del newUsuario debe estar hasheada, así que usamos el passwordEncoder que tenemos definido.
         ¿De dónde viene ese passwordEncoder?
         El objeto passwordEncoder está definido al principio de esta clase.
         */
        newUser.password = passwordEncoder.encode(usuario.password)


        // Guardamos el newUsuario en la base de datos... igual que siempre

        usuarioRepository.save(newUser)

        // Devolvemos el Usuario insertado en la BDD
        return usuario // Cambiar null por el usuario

    }



}