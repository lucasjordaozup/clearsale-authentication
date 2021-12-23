package com.teste.lucas.infrastructure.service

import com.teste.lucas.core.mappers.AuthenticateMapper
import com.teste.lucas.core.model.AuthenticateModel
import com.teste.lucas.core.model.AuthenticateResponse
import com.teste.lucas.core.port.AuthenticateServiceEntityPort
import com.teste.lucas.core.port.AuthenticateServicePort
import com.teste.lucas.entrypoint.controllers.AuthenticateController
import com.teste.lucas.infrastructure.clients.ExternalClient
import com.teste.lucas.infrastructure.request.LoginRequest
import com.teste.lucas.infrastructure.request.LoginResponse
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

//import org.graalvm.compiler.hotspot.replacements.Log.println

@Singleton
class AuthenticateService(
    private val client: ExternalClient
): AuthenticateServiceEntityPort {
    var log = LoggerFactory.getLogger(AuthenticateService::class.toString())

    override fun authenticate(request: LoginRequest): LoginResponse {
//        println("Efetuando login no cliente externo")
        return client.authenticate(request)
    }
}