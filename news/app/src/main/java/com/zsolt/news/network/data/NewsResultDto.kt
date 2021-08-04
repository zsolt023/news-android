package com.zsolt.news.network.data

import com.google.gson.annotations.SerializedName

data class NewsResultDto(
    @SerializedName("status") override val statusText: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
) : BaseDto(statusText)
