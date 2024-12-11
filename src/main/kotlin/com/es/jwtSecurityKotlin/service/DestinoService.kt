package com.es.jwtSecurityKotlin.service

import com.es.jwtSecurityKotlin.repository.DestinoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DestinoService {

    @Autowired
    lateinit var destinoRepository: DestinoRepository


}