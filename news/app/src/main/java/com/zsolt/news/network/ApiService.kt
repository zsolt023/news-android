package com.zsolt.news.network

import com.google.gson.GsonBuilder
import com.zsolt.news.network.data.NewsResultDto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    fun getNews(
        @Query("q") searchText: String? = null,
        @Query("apiKey") apiKey: String = API_KEY,
    ): Call<NewsResultDto>

    companion object {
        val HOST = "https://newsapi.org/"
        val API_KEY = "f71af7261c434b5d8be60816ed910d8b"
        fun create(): ApiService {
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(HOST)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}