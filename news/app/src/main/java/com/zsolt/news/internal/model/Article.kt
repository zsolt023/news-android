package com.zsolt.news.internal.model

import com.zsolt.news.internal.extensions.toTimeText
import com.zsolt.news.network.data.SourceDto
import java.util.*

data class Article(
    val source: SourceDto,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Date?,
    val content: String,
) {
    fun getPublishedAgo(): String {
        return publishedAt?.let {
            (System.currentTimeMillis() - it.time).toTimeText()
        } ?: ""
    }
}
