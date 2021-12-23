package com.teste.lucas.core.service

import br.com.iupp.middlewareauth.core.service.AuthenticateCacheService
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import com.teste.lucas.core.port.AuthenticateServicePort
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class AuthenticateService(
    private val cacheService: AuthenticateCacheService
): AuthenticateServicePort {

    @Value("\${external.url}")
    lateinit var url: String

    val username = "teste"
    val password = "teste123"

    val okHttpClient = OkHttpClient()

    var log = LoggerFactory.getLogger(AuthenticateService::class.toString())

    override fun authenticate(): String? {

        var token = this.obterTokenNoCache()

        if(this.obterTokenNoCache() !== null){
            log.info("Foi encontrado o token no cache: {}", token)
            return token
        }else{
            log.info("Não foi encontrado token no cache, indo na clearsale autenticar")
            val requestClearSale = JsonObject()
            val mediaType = MediaType.parse("application/json")
            log.info("url: {}", url)

            requestClearSale.addProperty("username", username)
            requestClearSale.addProperty("password", password)

            val body = RequestBody.create(mediaType, requestClearSale.toString())


            val requestClearSalesString = Request.Builder()
                .url("$url/login2")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build()

            val response = okHttpClient.newCall(requestClearSalesString).execute()
            val responseString = response.body().string()
            val responseJson = JsonParser.parseString(responseString).asJsonObject

            token = responseJson["Token"].asString
            var exp = responseJson["ExpirationDate"].asLong

            cacheService.saveTokenAuthenticateCache(token, exp)

            return token
        }
    }

    private fun obterTokenNoCache(): String?{
        var tokenCacheado = cacheService.readTokenAuthenticateCache()
        log.info("Token $tokenCacheado")
        if(tokenCacheado != null && tokenCacheado !== ""){
            return tokenCacheado
        }else{
            return null
        }
    }


}