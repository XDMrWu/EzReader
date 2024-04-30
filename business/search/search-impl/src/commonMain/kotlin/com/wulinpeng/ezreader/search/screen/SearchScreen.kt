package com.wulinpeng.ezreader.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.model.EzResult
import com.wulinpeng.ezreader.search.vm.SearchVM
import com.wulinpeng.ezreader.search.ui.SearchBar
import com.wulinpeng.ezreader.search.ui.SearchHistoryUI
import com.wulinpeng.ezreader.search.ui.SearchHotKeysUI
import com.wulinpeng.ezreader.search.ui.SearchResultList
import com.wulinpeng.ezreader.search.vm.SearchUIState
import org.koin.core.annotation.Factory


@Factory
class SearchScreen: ISearchScreen {
    @Composable
    override fun Content() {
        val vm = rememberScreenModel { SearchVM() }
        val navigator = LocalRootNavigator.current
        val uiState by vm.uiState.collectAsState(SearchUIState())
        var inputContent by remember { mutableStateOf("") }
        Column {
            SearchBar(inputContent, {inputContent = it}, vm::search) {
                if (uiState.searchResult != EzResult.None) {
                    vm.clearSearchResult()
                    inputContent = ""
                } else {
                    navigator.pop()
                }
            }
            if (uiState.searchResult != EzResult.None) {
                // 搜索结果
                SearchResultList(uiState.searchResult)
            } else {
                SearchHistoryUI(uiState.history, vm::clearHistory) {
                    inputContent = it
                    vm.search(it)
                }
                SearchHotKeysUI(uiState.hotKeys,)  {
                    inputContent = it
                    vm.search(it)
                }
            }
        }
        LaunchedEffect(Unit) {
            vm.loadHotKeys()
        }
    }

}