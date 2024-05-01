package com.wulinpeng.ezreader.book_shelf

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 16:20
 * @Description:
 */
interface IBookShelfService {
    /**
     * 是否已经添加了这本书
     */
    fun hasAddToShelf(bookId: String): Boolean

    /**
     * 添加书籍
     */
    fun addToShelf(bookId: String)

    /**
     * 移除书籍
     */
    fun removeFromShelf(bookId: String)
}