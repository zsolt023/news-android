package com.zsolt.news.internal.extensions

import com.zsolt.news.internal.model.Article
import com.zsolt.news.internal.model.Articles
import com.zsolt.news.network.data.ArticleDto
import com.zsolt.news.network.data.NewsResultDto
import java.text.SimpleDateFormat
import java.util.*

fun NewsResultDto.mapModel() = Articles(
    articles = articles.map {
        it.mapModel()
    }
)

fun ArticleDto.mapModel() = Article(
    source = source,
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(publishedAt),
    content = content,
)