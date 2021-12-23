package com.teste.lucas.infrastructure.clients

import com.teste.lucas.infrastructure.request.LoginRequest
import com.teste.lucas.infrastructure.request.LoginResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client(value = "\${external.url}/")
interface ExternalClient {

    @Post("login2")
    @Produces(MediaType.APPLICATION_JSON)
    @Headers(
        Header(name = "skipFilter", value = "false")
    )
    fun authenticate(@Body request: LoginRequest): LoginResponse

}