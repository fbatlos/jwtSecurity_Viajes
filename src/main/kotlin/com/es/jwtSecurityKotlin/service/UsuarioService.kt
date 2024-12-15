package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.Utils.Utils
import com.es.jwtSecurityKotlin.exception.ConflictoBD
import com.es.jwtSecurityKotlin.exception.NotFoundException
import com.es.jwtSecurityKotlin.exception.ValidationException
import com.es.jwtSecurityKotlin.model.Usuario
import com.es.jwtSecurityKotlin.repository.UsuarioRepository
import com.es.jwtSecurityKotlin.repository.ViajeRepository
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

    @Autowired
    private lateinit var viajeRepository: ViajeRepository


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
        usuario.username ?:  throw ValidationException("Es necesario un nombre")
        if(usuarioRepository.findByUsername(usuario.username!!).isPresent) {
            throw NotFoundException("El usuario ya existe.")
        }

       //Hacer util de telefono

        /*
         La password del newUsuario debe estar hasheada, así que usamos el passwordEncoder que tenemos definido.
         ¿De dónde viene ese passwordEncoder?
         El objeto passwordEncoder está definido al principio de esta clase.
         */
        if (usuario.password?.isBlank() == true || usuario.password!!.length < 5 ){
            throw ValidationException("Password no puede ser menor a 5 caracteres")
        }

        val newUser:Usuario = usuario

        if (usuario.telefono == ""){usuario.telefono = null }

        if (usuario.telefono != null){
            if (Utils.validarTelefono(usuario.telefono?:"1")){
                newUser.telefono  = usuario.telefono
            }else{
                throw ValidationException("Telefono no valido, puede dejar ese campo en blanco.")
            }
        }


        newUser.password = passwordEncoder.encode(usuario.password)


        // Guardamos el newUsuario en la base de datos... igual que siempre

        usuarioRepository.save(newUser)

        // Devolvemos el Usuario insertado en la BDD
        return usuario // Cambiar null por el usuario

    }

    fun eliminar(id: Long) : String {

        val usuario:Usuario = usuarioRepository.findById(id).orElseThrow { NotFoundException("Usuario no encontrado") }

        if (usuario.roles.equals("ADMIN")) {
            throw ConflictoBD("El usuario no se puede eliminar ya que es administrador.")
        }

        if ( viajeRepository.findAll().any { it.usuario?.id?.equals(usuario.id) == true }) {
            throw ConflictoBD("El usuario no puede ser eliminado ya que está asociado a 1 o más viajes.")
        }

        usuarioRepository.deleteById(id)
        return "El usuario fue eliminado con exito."

    }



}