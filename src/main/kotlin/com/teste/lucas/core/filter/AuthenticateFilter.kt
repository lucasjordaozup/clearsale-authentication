package com.teste.lucas.core.filter

import com.teste.lucas.core.port.AuthenticateServicePort
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory

@Filter
class AuthenticateFilter(
    private val authenticateServicePort: AuthenticateServicePort
): HttpClientFilter {

    override fun doFilter(request: MutableHttpRequest<*>?, chain: ClientFilterChain?): Publisher<out HttpResponse<*>> {
        var log = LoggerFactory.getLogger(AuthenticateFilter::class.toString())
        log.info("Entrou no filtro")

        val skipFilter = request?.headers!!["skipFilter"]
        log.info("Deve pular a inserção do authorization bearer token: $skipFilter")

        // Se o skipFilter for true ele pula a inserção do Authorization na requisição
        if(skipFilter == null || skipFilter === "" || skipFilter === "false"){
            var token = authenticateServicePort.authenticate()
            log.info("Token $token")

            if(token != null && token !== ""){
                return chain!!.proceed(request.bearerAuth(token))
            }else{
                log.error("Não foi possivel gerar o token")
            }

        }

        return chain!!.proceed(request)
    }
}