package com.zsolt.news.ui.main

import androidx.lifecycle.ViewModel
import com.zsolt.news.datasource.NetworkDatasource
import com.zsolt.news.internal.model.Article
import com.zsolt.news.network.NetworkErrorResponse
import com.zsolt.news.network.NetworkResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val networkDatasource: NetworkDatasource,
) : ViewModel() {

    /**
     * The item should contain the search text, or an empty string if there are no search text provided.
     */
    val refreshInput = PublishSubject.create<String>()
    val errorStream = PublishSubject.create<NetworkErrorResponse>()

    val newsStream: Observable<List<Article>> = refreshInput.flatMapMaybe { searchText ->
        networkDatasource.getNews(searchText).map { response ->
            when (response) {
                is NetworkResponse.Success -> response.data.articles
                is NetworkResponse.Failure -> {
                    errorStream.onNext(response.error)
                    emptyList()
                }
            }
        }
    }.doOnSubscribe {
        Completable.timer(200, TimeUnit.MILLISECONDS).subscribe { refreshInput.onNext("") }
    }
}