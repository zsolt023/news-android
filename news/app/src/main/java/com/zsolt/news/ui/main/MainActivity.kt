package com.zsolt.news.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsolt.news.R
import com.zsolt.news.databinding.ActivityMainBinding
import com.zsolt.news.internal.extensions.applyIoScheduler
import com.zsolt.news.internal.extensions.longSnackbar
import com.zsolt.news.ui.detail.ArticleActivity
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

        binding.search.setOnClickListener {
            // TODO: navigate to a new SearchActivity
        }

        ContextCompat.getDrawable(this, R.drawable.divider)?.let {
            val divider = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            divider.setDrawable(it)
            binding.articleList.addItemDecoration(divider)
        }

        viewModel.errorStream.applyIoScheduler().subscribe({
            val errorText = "Some error occurred: ${it.errorMsg()}"
            Timber.e(errorText)
            errorText.longSnackbar(binding.root)
        }, {}).apply { disposableBag.add(this) }

        viewModel.newsStream.applyIoScheduler().subscribe({ articles ->
            Timber.i("articles received: ${articles.size}")
            binding.articleList.adapter = ArticleAdapter(data = articles) { article ->
                val intent = Intent(this, ArticleActivity::class.java)
                intent.putExtra(ArticleActivity.ARTICLE, article)
                startActivity(intent)
            }
        }, Timber::e).apply { disposableBag.add(this) }
    }

    override fun onStop() {
        super.onStop()
        disposableBag.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}