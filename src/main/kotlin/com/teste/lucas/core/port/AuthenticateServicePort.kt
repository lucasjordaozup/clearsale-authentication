package com.teste.lucas.core.port

import com.teste.lucas.core.model.AuthenticateModel
import com.teste.lucas.core.model.AuthenticateResponse

interface AuthenticateServicePort {

    fun authenticate(request: AuthenticateModel): AuthenticateResponse

}