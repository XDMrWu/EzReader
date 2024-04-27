package com.wulinpeng.ezreader.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 14:45
 * @Description:
 */
object NetworkManager {

    lateinit var httpClient: HttpClient

    fun init() {
        httpClient = HttpClient {
            install(HttpTimeout) {
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 30000
                socketTimeoutMillis = 30000
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}