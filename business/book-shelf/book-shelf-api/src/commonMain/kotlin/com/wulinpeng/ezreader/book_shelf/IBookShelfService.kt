package com.wulinpeng.ezreader.book_shelf

import com.wulinpeng.ezreader.model.Book

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 16:20
 * @Description:
 */
interface IBookShelfService {
    /**
     * 是否已经添加了这本书
     */
    suspend fun hasAddToShelf(bookId: String): Boolean

    /**
     * 添加书籍
     */
    suspend fun addToShelf(book: Book)

    /**
     * 移除书籍
     */
    suspend fun removeFromShelf(bookId: String)
}