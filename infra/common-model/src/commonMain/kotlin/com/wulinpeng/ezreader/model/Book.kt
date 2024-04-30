package com.wulinpeng.ezreader.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val bookId: String,
    val name: String,
    val author: String,
    val image: String?,
    val desc: String?,
    val lastUpdateTime: String? = null,
    val lastUpdateChapter: String? = null,
    val category: String? = null,
    val chapterList: List<Chapter>? = null
)

@Serializable
data class Chapter(val title: String, val url: String, val source: String)