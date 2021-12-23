package com.teste.lucas.core.port

import com.teste.lucas.infrastructure.request.LoginRequest
import com.teste.lucas.infrastructure.request.LoginResponse

interface AuthenticateServiceEntityPort {
    fun authenticate(request: LoginRequest): LoginResponse
}