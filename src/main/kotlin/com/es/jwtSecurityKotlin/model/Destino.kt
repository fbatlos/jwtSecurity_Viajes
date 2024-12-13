package com.es.jwtSecurityKotlin.model

import jakarta.persistence.*

@Entity
@Table(name = "destinos")
data class Destino(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idDestino: Long? = null,

    @Column(nullable = false, length = 100)
    var nombre: String,

    @Column(nullable = false, length = 50)
    var pais: String,

    @Column(length = 1000)
    var descripcion: String? = null,

    @Column(nullable = false, length = 50)
    var origen : String
)
