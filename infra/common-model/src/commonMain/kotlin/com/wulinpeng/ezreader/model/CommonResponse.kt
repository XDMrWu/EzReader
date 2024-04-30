package com.wulinpeng.ezreader.model

import kotlinx.serialization.Serializable

/**
 * @Author: wulinpeng
 * @Date: 2024/4/30 15:55
 * @Description:
 */
@Serializable
data class CommonResponse<T>(
    val code: Int,
    val msg: String,
    val data: T
) {
    fun isSuccess() = code == 0
}