package com.zsolt.news.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.zsolt.news.databinding.ActivityArticleBinding
import com.zsolt.news.internal.model.Article

class ArticleActivity: AppCompatActivity() {

    private var _binding: ActivityArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            finish()
        }

        val article = intent.getSerializableExtra(ARTICLE) as Article
        updateUI(article)
    }

    private fun updateUI(article: Article) {
        Picasso.get().load(article.urlToImage).into(binding.image)
        binding.source.text = article.source.name
        binding.title.text = article.title
        binding.content.text = article.content

        binding.buttonShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, article.url)
            startActivity(Intent.createChooser(intent, "Share"))
        }

        binding.buttonFullArticle.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(article.url)
            startActivity(i)
        }
    }

    companion object {
        const val ARTICLE = "param_article"
    }
}