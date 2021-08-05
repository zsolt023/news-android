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

    //val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

    override fun getNews(searchText: String?): Maybe<NetworkResponse<Articles>> {
        /*val now = Calendar.getInstance()
        val toDate = dateFormatter.format(now.time)
        now.add(Calendar.DATE, -1)
        val fromDate = dateFormatter.format(now.time)*/
        return apiService.getHeadlines(
            countryCode = "us",
        ).toMaybeCall { newsResultDto ->
            newsResultDto.mapModel()
        }
    }

}