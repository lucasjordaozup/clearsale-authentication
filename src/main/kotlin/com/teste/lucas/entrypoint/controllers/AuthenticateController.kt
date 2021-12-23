package com.teste.lucas.entrypoint.controllers

import com.teste.lucas.infrastructure.clients.ExternalClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory

//import org.graalvm.compiler.hotspot.replacements.Log.println

@Controller("/auth")
class AuthenticateController(
//    private val service: AuthenticateServicePort
private val externalClient: ExternalClient
) {

    var log = LoggerFactory.getLogger(AuthenticateController::class.toString())

    @Post("/login")
    fun authenticate(): HttpResponse<*>{
        log.info("Iniciando o controller")
        externalClient.order()
        return HttpResponse.ok("ook")
    }

}