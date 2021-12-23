package com.teste.lucas.infrastructure.request

import java.util.*

data class LoginResponse(
    var token: String = UUID.randomUUID().toString(),
    var exp: Long = 15000
)
