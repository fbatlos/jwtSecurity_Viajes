package com.example.unsecuredseguros.exception

class ConflictoBD (message: String)
    : Exception("Error el dato ya existe en la bd (409). $message")