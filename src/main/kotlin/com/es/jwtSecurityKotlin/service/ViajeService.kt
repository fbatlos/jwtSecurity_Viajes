package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.repository.ViajeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ViajeService {

    @Autowired
    lateinit var viajeRepository: ViajeRepository


}