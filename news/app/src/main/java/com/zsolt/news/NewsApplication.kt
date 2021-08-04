package com.zsolt.news

import android.app.Application
import com.zsolt.news.di.mainModule
import com.zsolt.news.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initDiFramework()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initDiFramework() {
        startKoin {
            androidContext(this@NewsApplication)
            modules(
                listOf(
                    viewModelModule,
                    mainModule,
                )
            )
        }
    }
}