﻿package com.example.unsecuredseguros.exception

class NotFoundException (message: String)
    : Exception("Error no se ha encontrado (404). $message")