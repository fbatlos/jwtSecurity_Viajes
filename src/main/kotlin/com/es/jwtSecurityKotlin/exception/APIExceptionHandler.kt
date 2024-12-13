package com.example.unsecuredseguros.exception

import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class APIExceptionHandler {


    @ExceptionHandler(IllegalArgumentException::class , NumberFormatException::class, ValidationException::class , AccessDeniedException::class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody//la respuesta va en formato JSON
    //                      todo lo que viaja   /  la excepttion como tal
    fun handleBadRequest(request:HttpServletRequest, e:Exception):ErrorParaCliente{
        return ErrorParaCliente(message = e.message, uri = request.requestURI)
    }


    @ExceptionHandler(NotFoundException::class , EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody//la respuesta va en formato JSON
    //                      todo lo que viaja   /  la excepttion como tal
    fun handleNotFound(request:HttpServletRequest, e:Exception):ErrorParaCliente{
        return ErrorParaCliente(message = e.message, uri = request.requestURI)
    }

}