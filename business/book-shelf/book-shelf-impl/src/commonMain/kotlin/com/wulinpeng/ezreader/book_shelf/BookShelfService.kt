package com.wulinpeng.ezreader.book_shelf

import com.wulinpeng.ezreader.book_shelf.repo.BookShelfRepo
import com.wulinpeng.ezreader.model.Book
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 16:21
 * @Description:
 */
@Single
class BookShelfService: IBookShelfService, KoinComponent {

    private val bookShelfRepo by inject<BookShelfRepo>()

    override suspend fun hasAddToShelf(bookId: String): Boolean {
        return bookShelfRepo.hasAddToShelf(bookId)
    }

    override suspend fun addToShelf(book: Book) {
        bookShelfRepo.addToShelf(book)
    }

    override suspend fun removeFromShelf(bookId: String) {
        bookShelfRepo.removeFromShelf(bookId)
    }
}