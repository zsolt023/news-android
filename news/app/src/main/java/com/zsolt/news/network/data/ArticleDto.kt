package com.zsolt.news.network.data

data class ArticleDto(
    val source: SourceDto,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    /**
     * This variable holds a date in a format of: "2021-07-19T11:00:00Z"
     */
    val publishedAt: String,
    val content: String,
)
