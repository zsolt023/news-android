package com.zsolt.news.di

import com.zsolt.news.datasource.NetworkDatasource
import com.zsolt.news.datasource.NetworkDatasourceImpl
import com.zsolt.news.network.ApiService
import com.zsolt.news.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val mainModule = module {
    single { ApiService.create() }
    single<NetworkDatasource> { NetworkDatasourceImpl(get()) }
}
