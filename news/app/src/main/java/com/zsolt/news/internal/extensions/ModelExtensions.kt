package com.zsolt.news.internal.extensions

import com.zsolt.news.internal.model.Article
import com.zsolt.news.internal.model.Articles
import com.zsolt.news.internal.model.Source
import com.zsolt.news.network.data.ArticleDto
import com.zsolt.news.network.data.NewsResultDto
import com.zsolt.news.network.data.SourceDto
import java.text.SimpleDateFormat
import java.util.*

fun NewsResultDto.mapModel() = Articles(
    articles = articles.map {
        it.mapModel()
    }
)

fun ArticleDto.mapModel() = Article(
    source = source.mapModel(),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).parse(publishedAt),
    content = content,
)

fun SourceDto.mapModel() = Source(
    id = id,
    name = name,
)