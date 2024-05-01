package com.wulinpeng.ezreader.book_shelf

import org.koin.core.annotation.Single

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 16:21
 * @Description:
 */
@Single
class BookShelfService: IBookShelfService {
    override fun hasAddToShelf(bookId: String): Boolean {
        return false
    }

    override fun addToShelf(bookId: String) {
    }

    override fun removeFromShelf(bookId: String) {
    }
}