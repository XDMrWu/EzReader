package com.wulinpeng.ezreader.book_detail.repo

import com.wulinpeng.ezreader.CommonConfig
import com.wulinpeng.ezreader.book_shelf.IBookShelfService
import com.wulinpeng.ezreader.model.Book
import com.wulinpeng.ezreader.model.CommonResponse
import com.wulinpeng.ezreader.model.EzResult
import com.wulinpeng.ezreader.network.NetworkManager
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

/**
 * @Author: wulinpeng
 * @Date: 2024/5/1 16:22
 * @Description:
 */
class BookDetailRepo: KoinComponent {

    companion object {
        val BOOK_DETAIL_URL = "http://${CommonConfig.ezreader_host}/ezreader/detail"
    }

    suspend fun loadBook(bookId: String): EzResult<Pair<Book, Boolean>> {
        val response = NetworkManager.httpClient.get(BOOK_DETAIL_URL) {
            parameter("bookId", bookId)
        }
        return if (response.status.value in 200..299) {
            val data = response.body<CommonResponse<Book>>()
            if (data.isSuccess()) {
                val hasAdd = get<IBookShelfService>().hasAddToShelf(bookId)
                EzResult.Success(Pair(data.data, hasAdd))
            } else {
                EzResult.Error(msg = data.msg)
            }
        } else {
            // 搜索失败
            EzResult.Error(msg = "获取书籍详情失败")
        }
    }
}