package com.es.jwtSecurityKotlin.model

import jakarta.persistence.*

@Entity
@Table(name = "destinos")
data class Destino(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idDestino: Long = 0,

    @Column(nullable = false, length = 100)
    val nombre: String,

    @Column(nullable = false, length = 50)
    val pais: String,

    @Column(length = 1000)
    val descripcion: String? = null,

    @Column(nullable = false, length = 50)
    val origen : String
)
