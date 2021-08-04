package com.zsolt.news.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zsolt.news.databinding.ActivityMainBinding
import com.zsolt.news.internal.extensions.applyIoScheduler
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by inject()
    private val disposableBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        viewModel.errorStream.applyIoScheduler().subscribe({
            Timber.e("Some error occurred: ${it.errorMsg()}")
        }, {}).apply { disposableBag.add(this) }

        viewModel.newsStream.applyIoScheduler().subscribe({ articles ->
            Timber.i("articles received: ${articles.size}")
        }, Timber::e).apply { disposableBag.add(this) }
    }

    override fun onStop() {
        super.onStop()
        disposableBag.clear()
    }
}