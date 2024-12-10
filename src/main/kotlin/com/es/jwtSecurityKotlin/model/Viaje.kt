package com.es.jwtSecurityKotlin.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "viajes")
data class Viaje(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idViaje: Long = 0,

    @Column(nullable = false, length = 100)
    val titulo: String,

    @Column(length = 1000)
    val descripcion: String? = null,

    @Column(nullable = false)
    val fecha_Ida: LocalDate,

    @Column(nullable = false)
    val fecha_Regreso: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizador", nullable = false)
    val organizador: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino", nullable = false)
    val destino: Destino
)
