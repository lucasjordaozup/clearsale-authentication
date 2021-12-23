package br.com.iupp.middlewareauth.core.service

import io.lettuce.core.RedisClient
import jakarta.inject.Singleton
import java.time.Duration

@Singleton
class AuthenticateCacheService(
    private val redisClient: RedisClient
) {

    var connection = redisClient.connect()
    var commands = connection.sync()

    fun saveTokenAuthenticateCache(token: String, expMillis: Long) {
        commands.set("tokenAuthenticate", token)
        commands.expire("tokenAuthenticate", Duration.ofMillis(expMillis))
    }

    fun readTokenAuthenticateCache(): String? {
        return commands.get("tokenAuthenticate")
    }

    fun deleteTokenAuthenticateCache(): Long? {
        return commands.del("tokenAuthenticate")
    }

}