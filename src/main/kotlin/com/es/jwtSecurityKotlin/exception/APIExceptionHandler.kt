﻿package com.es.jwtSecurityKotlin.exception

import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class APIExceptionHandler {


    @ExceptionHandler(IllegalArgumentException::class , NumberFormatException::class, ValidationException::class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody//la respuesta va en formato JSON
    //                      todo lo que viaja   /  la excepttion como tal
    fun handleBadRequest(request:HttpServletRequest, e:Exception):ErrorParaCliente{
        return ErrorParaCliente(message = e.message, uri = request.requestURI)
    }

    @ExceptionHandler(AccessDeniedException::class )
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody//la respuesta va en formato JSON
    fun handleForbidden(request:HttpServletRequest, e:Exception):ErrorParaCliente{
        return ErrorParaCliente(message = e.message, uri = request.requestURI)
    }

    @ExceptionHandler(NotFoundException::class , EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody//la respuesta va en formato JSON
    //                      todo lo que viaja   /  la excepttion como tal
    fun handleNotFound(request:HttpServletRequest, e:Exception):ErrorParaCliente{
        return ErrorParaCliente(message = e.message, uri = request.requestURI)
    }

    @ExceptionHandler(ConflictoBD::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody//la respuesta va en formato JSON
    fun handleConflict(request:HttpServletRequest, e:Exception):ErrorParaCliente{
        return ErrorParaCliente(message = e.message, uri = request.requestURI)
    }
}