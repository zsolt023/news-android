package com.zsolt.news.datasource

import com.zsolt.news.internal.model.Articles
import com.zsolt.news.network.NetworkResponse
import io.reactivex.Maybe

interface NetworkDatasource {
    fun getNews(searchText: String? = null): Maybe<NetworkResponse<Articles>>
}