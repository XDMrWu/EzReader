package com.wulinpeng.ezreader.book_shelf.vm

import com.wulinpeng.ezreader.architecture.viewmodel.EzViewModel
import com.wulinpeng.ezreader.book_shelf.repo.BookShelfRepo
import com.wulinpeng.ezreader.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.core.component.get

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 17:45
 * @Description:
 */

enum class BookShelfMode {
    List, // 列表模式
    Grid // 宫格模式
}

data class BookShelfUIState(
    val bookList: List<Book> = emptyList(),
    val mode: BookShelfMode = BookShelfMode.List
)

class BookShelfVM: EzViewModel() {

    private val repo = get<BookShelfRepo>()
    private val bookShelfFlow = repo.bookShelfFlow
    private val modeFlow = MutableStateFlow(BookShelfMode.List)

    val uiStateFlow = combine(bookShelfFlow, modeFlow) { bookList, mode ->
        BookShelfUIState(bookList ?: emptyList(), mode)
    }

    fun addToShelf(book: Book) {
        launch {
            repo.addToShelf(book)
        }
    }

    fun removeFromShelf(book: Book) {
        launch {
            repo.removeFromShelf(book.bookId)
        }
    }

    fun changeMode(mode: BookShelfMode) {
        modeFlow.value = mode
    }
}