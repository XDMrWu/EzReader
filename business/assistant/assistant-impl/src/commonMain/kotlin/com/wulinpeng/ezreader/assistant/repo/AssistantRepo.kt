package com.wulinpeng.ezreader.assistant.repo

import com.wulinpeng.ezreader.CommonConfig
import com.wulinpeng.ezreader.appcontext.navigator.defaultJson
import com.wulinpeng.ezreader.network.NetworkManager
import com.wulinpeng.ezreader.network.sse.readSse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * @Author: wulinpeng
 * @Date: 2024/4/27 16:38
 * @Description:
 */
class AssistantRepo {

    val debug = true

    fun getAnswer(question: String): Flow<String> {
        return NetworkManager.httpClient
            .readSse("http://${CommonConfig.ezreader_host}/ezreader/chatBot", params = mapOf("query" to question, "fake" to debug))
            .map {
                runCatching {
                    defaultJson.decodeFromString<JsonObject>(it.data).get("content")!!.jsonPrimitive.content
                }.getOrNull() ?: ""
            }
    }
}