package com.es.jwtSecurityKotlin.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "viajes")
data class Viaje(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idViaje: Long? = null,

    @Column(nullable = false, length = 100)
    var titulo: String,

    @Column(length = 1000)
    var descripcion: String? = null,

    @Column(nullable = false)
    var fecha_Ida: LocalDate,

    @Column(nullable = false)
    var fecha_Regreso: LocalDate,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    var usuario: Usuario?,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_destino", nullable = false)
    var destino: Destino
)
