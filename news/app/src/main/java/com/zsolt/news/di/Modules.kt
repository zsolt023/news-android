package com.zsolt.news.di

import com.zsolt.news.network.ApiService
import org.koin.dsl.module

val viewModelModule = module {
}

val mainModule = module {
    module { ApiService.create() }
}
