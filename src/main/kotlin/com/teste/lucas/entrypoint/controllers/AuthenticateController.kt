package com.teste.lucas.entrypoint.controllers

import com.teste.lucas.core.model.AuthenticateModel
import com.teste.lucas.core.port.AuthenticateServicePort
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory

//import org.graalvm.compiler.hotspot.replacements.Log.println

@Controller("/auth")
class AuthenticateController(
    private val service: AuthenticateServicePort
) {

    var log = LoggerFactory.getLogger(AuthenticateController::class.toString())

    @Post("/login")
    fun login(): HttpResponse<*>{
        log.info("Iniciando o controller")
        return HttpResponse.ok(service.authenticate(AuthenticateModel("Teste", "12234")))
    }

}