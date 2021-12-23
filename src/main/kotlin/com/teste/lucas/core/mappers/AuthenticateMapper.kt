package com.teste.lucas.core.mappers

import com.teste.lucas.core.model.AuthenticateModel
import com.teste.lucas.core.model.AuthenticateResponse
import com.teste.lucas.infrastructure.request.LoginRequest
import com.teste.lucas.infrastructure.request.LoginResponse

class AuthenticateMapper {
    companion object{
        fun authenticateModelToLoginRequest(request: AuthenticateModel) = LoginRequest(
            request.username,
            request.password
        )

        fun loginResponseToAuthenticateResponse(response: LoginResponse) = AuthenticateResponse(response.message)

    }
}
