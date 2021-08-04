package com.zsolt.news.datasource

import com.zsolt.news.internal.extensions.mapModel
import com.zsolt.news.internal.extensions.toMaybeCall
import com.zsolt.news.internal.model.Articles
import com.zsolt.news.network.ApiService
import com.zsolt.news.network.NetworkResponse
import io.reactivex.Maybe

class NetworkDatasourceImpl(
    private val apiService: ApiService,
) : NetworkDatasource {

    override fun getNews(searchText: String?): Maybe<NetworkResponse<Articles>> =
        apiService.getNews(searchText = searchText).toMaybeCall { newsResultDto ->
            newsResultDto.mapModel()
        }

}