package com.wulinpeng.ezreader.search.vm

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.wulinpeng.ezreader.architecture.viewmodel.EzViewModel
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.model.EzResult
import com.wulinpeng.ezreader.search.repo.SearchRepo
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.inject

/**
 * @Author: wulinpeng
 * @Date: 2024/4/29 21:54
 * @Description:
 */

data class SearchUIState(
    val history: List<String> = emptyList(),
    val hotKeys: List<String> = emptyList(),
    val searchResult: EzResult<List<Book>> = EzResult.None,
)

class SearchVM: EzViewModel() {

    val repo by inject<SearchRepo>()
    private val searchResultFlow = MutableStateFlow<EzResult<List<Book>>>(EzResult.None)
    val uiState = combine(repo.hotKeysFlow, repo.historyFlow, searchResultFlow) {hotKeys, history, searchResult ->
        SearchUIState(history ?: emptyList(), hotKeys, searchResult)
    }

    fun loadHotKeys() {
        launch {
            delay(1000)
            repo.loadHotKeys()
        }
    }

    fun search(content: String) {
        launch {
            searchResultFlow.emit(EzResult.Loading)
            searchResultFlow.emit(repo.search(content))
        }
    }

    fun clearHistory() {
        launch {
            repo.clearHistory()
        }
    }

    fun clearSearchResult() {
        coroutineContext.cancelChildren()
        launch {
            searchResultFlow.emit(EzResult.None)
        }
    }
}