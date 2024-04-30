package com.wulinpeng.ezreader.model

sealed interface EzResult<out T> {
    data class Success<T>(val data: T) : EzResult<T>
    data class Error(val exception: Throwable? = null, val msg: String? = null) : EzResult<Nothing>
    object Loading : EzResult<Nothing>
    object None : EzResult<Nothing> // 表示请求未发送
}