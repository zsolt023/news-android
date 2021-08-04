package com.zsolt.news.network.data

data class NewsResultDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)
