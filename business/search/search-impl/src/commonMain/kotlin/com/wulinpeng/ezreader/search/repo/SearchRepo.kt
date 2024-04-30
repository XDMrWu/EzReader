package com.wulinpeng.ezreader.search.repo

import com.wulinpeng.ezreader.CommonConfig
import com.wulinpeng.ezreader.appcontext.navigator.defaultJson
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.model.CommonResponse
import com.wulinpeng.ezreader.model.EzResult
import com.wulinpeng.ezreader.network.NetworkManager
import com.wulinpeng.ezreader.storage.StorageManager
import io.github.xxfast.kstore.file.storeOf
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import okio.Path.Companion.toPath
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent

/**
 * @Author: wulinpeng
 * @Date: 2024/4/29 22:29
 * @Description:
 */
@Single
class SearchRepo: KoinComponent {

    companion object {
        val HISTORY_FILE = "${StorageManager.storageDir}/search_history.json"
        val SEARCH_URL = "http://${CommonConfig.ezreader_host}/ezreader/search"
        val HOT_KEYS_URL = "http://${CommonConfig.ezreader_host}/ezreader/hotWords"
    }

    private val historyStore = storeOf<List<String>>(file = HISTORY_FILE.toPath(), default = emptyList(), json = defaultJson)
    val historyFlow = historyStore.updates
    val hotKeysFlow = MutableStateFlow<List<String>>(emptyList())


    suspend fun loadHotKeys() {
        if (hotKeysFlow.replayCache.lastOrNull()?.isNotEmpty() == true) {
            return
        }
        val response = runCatching {
            NetworkManager.httpClient.get(HOT_KEYS_URL)
        }.onFailure {
            it.printStackTrace()
        }.getOrThrow()
        if (response.status.value in 200..299) {
            val data = response.body<CommonResponse<List<String>>>()
            if (data.isSuccess()) {
                hotKeysFlow.emit(data.data)
            } else {
                hotKeysFlow.emit(emptyList())
            }
        } else {
            // 搜索失败
            hotKeysFlow.emit(emptyList())
        }
    }

    suspend fun search(content: String): EzResult<List<Book>> {
        historyStore.update {
            (it?.toMutableList() ?: mutableListOf()).apply {
                if (!contains(content)) {
                    add(0, content)
                } else {
                    remove(content)
                    add(0, content)
                }
            }
        }
        val response = NetworkManager.httpClient.get(SEARCH_URL) {
            parameter("key", content)
        }
        return if (response.status.value in 200..299) {
            val data = response.body<CommonResponse<List<Book>>>()
            if (data.isSuccess()) {
                EzResult.Success(data.data)
            } else {
                EzResult.Error(msg = data.msg)
            }
        } else {
            // 搜索失败
            EzResult.Error(msg = response.status.description)
        }

    }

    suspend fun clearHistory() {
        historyStore.reset()
    }

}