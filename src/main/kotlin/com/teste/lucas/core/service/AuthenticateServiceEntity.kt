package com.teste.lucas.core.service

import com.teste.lucas.core.mappers.AuthenticateMapper
import com.teste.lucas.core.model.AuthenticateModel
import com.teste.lucas.core.model.AuthenticateResponse
import com.teste.lucas.core.port.AuthenticateServiceEntityPort
import com.teste.lucas.core.port.AuthenticateServicePort
import com.teste.lucas.entrypoint.controllers.AuthenticateController
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class AuthenticateServiceEntity(private val authenticateServiceEntityPort: AuthenticateServiceEntityPort): AuthenticateServicePort {

    var log = LoggerFactory.getLogger(AuthenticateServiceEntity::class.toString())

    override fun authenticate(request: AuthenticateModel): AuthenticateResponse {
        log.info("Iniciando o authenticate do service entity")
        val response = authenticateServiceEntityPort.authenticate(AuthenticateMapper.authenticateModelToLoginRequest(request))
        log.info("Passou do authenticate do service entity")
        return AuthenticateMapper.loginResponseToAuthenticateResponse(response)
    }
}