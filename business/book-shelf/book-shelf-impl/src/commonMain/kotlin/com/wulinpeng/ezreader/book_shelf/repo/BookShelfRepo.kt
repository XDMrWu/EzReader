package com.wulinpeng.ezreader.book_shelf.repo

import com.wulinpeng.ezreader.appcontext.navigator.defaultJson
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.storage.StorageManager
import io.github.xxfast.kstore.file.storeOf
import okio.Path.Companion.toPath
import org.koin.core.annotation.Single

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 18:32
 * @Description:
 */
@Single
class BookShelfRepo {
    companion object {
        val SHELF_FILE = "${StorageManager.storageDir}/book_shelf.json"
    }

    private val bookShelfStore = storeOf<List<Book>>(file = SHELF_FILE.toPath(), default = emptyList(), json = defaultJson)
    val bookShelfFlow = bookShelfStore.updates

    suspend fun hasAddToShelf(bookId: String): Boolean {
        return bookShelfStore.get()?.any {
            it.bookId == bookId
        } ?: false
    }

    suspend fun addToShelf(book: Book) {
        bookShelfStore.update {
            (it?.toMutableList() ?: mutableListOf()).apply {
                if (none { it.bookId == book.bookId }) {
                    add(book)
                }
            }
        }
    }

    suspend fun removeFromShelf(bookId: String) {
        bookShelfStore.update {
            (it?.toMutableList() ?: mutableListOf()).apply {
                removeAll { it.bookId == bookId }
            }
        }
    }

}