package dev.suresh.coreNetwork.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.content.EntityTagVersion
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {

    fun getInstance() : HttpClient{
        return HttpClient {
            install(ContentNegotiation){
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout){
                requestTimeoutMillis = 10000
                connectTimeoutMillis = 10000
                socketTimeoutMillis =  10000
            }

            install(DefaultRequest){
                url {
                    host = "api.themoviedb.org"
                    protocol = URLProtocol.HTTPS

                }
                header(
                    HttpHeaders.Authorization,
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OGYwNTdiYTRiODA3MjdhNjc4MmM4MWUzMzQzOWFlZSIsIm5iZiI6MTc3MDgwODA5MC44MDYwMDAyLCJzdWIiOiI2OThjNjMxYWIyNzYyZTc1NGY2N2VhYzYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.Bn1wSD5qA4SyvMfyQlBb_sOQHeUsTD9CtmDsuERxeEI"
                )
            }
        }
    }
}