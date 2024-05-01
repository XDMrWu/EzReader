package com.wulinpeng.ezreader.book_detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.rememberScreenModel
import com.wulinpeng.ezreader.appcontext.navigator.LocalRootNavigator
import com.wulinpeng.ezreader.book_detail.ui.BookDetailUI
import com.wulinpeng.ezreader.book_detail.vm.BookDetailUIState
import com.wulinpeng.ezreader.book_detail.vm.BookDetailVM
import com.wulinpeng.ezreader.model.EzResult
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 00:21
 * @Description:
 */

@Factory
class BookDetailScreen(@InjectedParam val bookId: String): IBookDetailScreen {

    @Composable
    override fun Content() {
        val vm = rememberScreenModel { BookDetailVM() }
        val uiState by vm.uiState
        Column(
            Modifier.fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            when (uiState) {
                is EzResult.Loading -> {
                    // 加载中
                    Box(Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is EzResult.Error -> {
                    Box(Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {
                        Button(onClick = {
                            vm.loadBook(bookId)
                        }) {
                            // 重新加载
                            Text("加载失败，点击重试")
                        }
                    }
                }
                is EzResult.Success -> {
                    val data = (uiState as EzResult.Success<BookDetailUIState>).data
                    BookDetailUI(vm, data.book!!, data.hasAddToShelf)
                }
                is EzResult.None -> {}
            }
        }
        LaunchedEffect(Unit) {
            vm.loadBook(bookId)
        }
    }
}