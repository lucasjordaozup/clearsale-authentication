package com.teste.lucas.core.filter

import br.com.iupp.middlewareauth.core.service.AuthenticateCacheService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import io.netty.util.internal.StringUtil
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory
import java.util.*

@Filter
class AuthenticateFilter(
    private val cacheService: AuthenticateCacheService
): HttpClientFilter {
    override fun doFilter(request: MutableHttpRequest<*>?, chain: ClientFilterChain?): Publisher<out HttpResponse<*>> {

        var log = LoggerFactory.getLogger(AuthenticateFilter::class.toString())

        val skipFilter = request?.headers!!["skipFilter"]

        log.info("$skipFilter")

        if(skipFilter == null || skipFilter === "" || skipFilter === "false"){
            var token = cacheService.readTokenAuthenticateCache()
            log.info("Token $token")
            if(token != null && token !== ""){
                return chain!!.proceed(request.bearerAuth(token))
            }else{
                // Aqui devemos chamar o service que vai chamar o /authenticate que criamos que vai bater na clearsale e vai trazer o token e o Exp
                token = UUID.randomUUID().toString()
                cacheService.saveTokenAuthenticateCache(token, 10000)
                return chain!!.proceed(request.bearerAuth(token))
            }

        }

        return chain!!.proceed(request)
    }
}