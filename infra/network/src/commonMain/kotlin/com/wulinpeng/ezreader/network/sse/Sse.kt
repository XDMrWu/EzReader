package com.wulinpeng.ezreader.network.sse

import com.wulinpeng.ezreader.sse.EventId
import com.wulinpeng.ezreader.sse.SseEvent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.parameter.parametersOf

internal const val HEADER_LAST_EVENT_ID = "Last-Event-Id"

public typealias Milliseconds = Long

internal typealias HeadersProvider = suspend (EventId?) -> Map<String, String>
internal typealias QueryParamsProvider = suspend (EventId?) -> Any

public class UnauthorizedError : Throwable()
public class NotEventStreamError : Throwable()

public fun HttpClient.readSse(
    url: String,
    headersProvider: HeadersProvider = { it?.let { mapOf(HEADER_LAST_EVENT_ID to it) } ?: emptyMap() },
    params: Map<String, Any> = emptyMap(),
): Flow<SseEvent> {
    var lastEventId: String? = null
    return flow {
        coroutineScope {
            val customHeaders = headersProvider(lastEventId)
            prepareRequest(
                url = url,
                headers = customHeaders,
                params = params
            ).execute { response ->
                if (!response.status.isSuccess()) {
                    throw UnauthorizedError()
                }
                if (!response.isEventStream()) {
                    throw NotEventStreamError()
                }
                response.bodyAsChannel()
                    .readSse(
                        onSseEvent = { sseEvent ->
                            lastEventId = sseEvent.id
                            emit(sseEvent)
                        }
                    )
            }
        }
    }
}

private suspend fun HttpClient.prepareRequest(
    url: String,
    headers: Map<String, String> = emptyMap(),
    params: Map<String, Any>
): HttpStatement =
    prepareGet(url) {
        headers {
            append(HttpHeaders.Accept, "text/event-stream")
            append(HttpHeaders.CacheControl, "no-cache")
            append(HttpHeaders.Connection, "keep-alive")
            headers.forEach { (key, value) -> append(key, value) }
        }
        url {
            params.forEach { (key, value) -> parameters.append(key, value.toString()) }
        }
        timeout {
            requestTimeoutMillis = 600000
        }
    }

private fun HttpResponse.isEventStream(): Boolean {
    val contentType = contentType() ?: return false
    return contentType.contentType == "text" && contentType.contentSubtype == "event-stream"
}

private fun HttpRequestBuilder.addOrReplaceParameter(key: String, value: String?): Unit =
    value?.let {
        url.parameters.remove(key)
        url.parameters.append(key, it)
    } ?: Unit