package com.wulinpeng.ezreader.search.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.model.EzResult

/**
 * @Author: wulinpeng
 * @Date: 2024/4/30 15:31
 * @Description:
 */
@Composable
fun SearchResultList(searchResult: EzResult<List<Book>>) {
    when (searchResult) {
        is EzResult.Error -> Text("Error")
        is EzResult.Loading -> Text("Loading")
        is EzResult.Success -> {
            LazyColumn {
                items(searchResult.data) {
                    BookItem(it)
                }
            }
        }
        EzResult.None -> {}
    }
}

@Composable
fun BookItem(book: Book) {
    TODO("Not yet implemented")
}
