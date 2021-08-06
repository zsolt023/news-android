package com.zsolt.news.di

import com.zsolt.news.datasource.NetworkDatasource
import com.zsolt.news.datasource.NetworkDatasourceImpl
import com.zsolt.news.network.ApiService
import com.zsolt.news.ui.main.MainViewModel
import com.zsolt.news.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}

val mainModule = module {
    single { ApiService.create() }
    single<NetworkDatasource> { NetworkDatasourceImpl(get()) }
}
