package com.wulinpeng.ezreader.book_detail.vm

import androidx.compose.runtime.mutableStateOf
import com.wulinpeng.ezreader.architecture.viewmodel.EzViewModel
import com.wulinpeng.ezreader.book_detail.repo.BookDetailRepo
import com.wulinpeng.ezreader.book_shelf.IBookShelfService
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.model.EzResult
import kotlinx.coroutines.launch
import org.koin.core.component.get

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 00:25
 * @Description:
 */

data class BookDetailUIState(
    val book: Book? = null,
    val hasAddToShelf: Boolean = false
)

class BookDetailVM: EzViewModel() {
    private val repo = BookDetailRepo()
    val uiState = mutableStateOf<EzResult<BookDetailUIState>>(EzResult.None)


    fun loadBook(bookId: String) {
        uiState.value = EzResult.Loading
        launch {
            val result = repo.loadBook(bookId)
            when (result) {
                is EzResult.Error -> uiState.value = result
                is EzResult.Success -> uiState.value = EzResult.Success(BookDetailUIState(result.data.first, result.data.second))
                else -> {}
            }
        }
    }

    fun addToShelf(book: Book) {
        launch {
            get<IBookShelfService>().addToShelf(book)
        }
        if (uiState.value is EzResult.Success) {
            uiState.value = (uiState.value as EzResult.Success).data.copy(hasAddToShelf = true).let { EzResult.Success(it) }
        }
    }

    fun removeFromShelf(bookId: String) {
        launch {
            get<IBookShelfService>().removeFromShelf(bookId)
        }
        if (uiState.value is EzResult.Success) {
            uiState.value = (uiState.value as EzResult.Success).data.copy(hasAddToShelf = false).let { EzResult.Success(it) }
        }
    }

}