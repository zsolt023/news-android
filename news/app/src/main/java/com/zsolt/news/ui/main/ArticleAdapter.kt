package com.zsolt.news.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zsolt.news.R
import com.zsolt.news.databinding.ItemHighlightedArticleBinding
import com.zsolt.news.databinding.ItemNormalArticleBinding
import com.zsolt.news.internal.model.Article
import timber.log.Timber

class ArticleAdapter(
    private val data: List<Article>,
    private val clickHandler: (Article) -> Unit = {},
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val VIEW_TYPE_HIGHLIGHTED = 0
    private val VIEW_TYPE_NORMAL = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            /*VIEW_TYPE_HIGHLIGHTED -> HighlightedViewHolder(
                ItemHighlightedArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_NORMAL -> NormalViewHolder(
                ItemNormalArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )*/
            VIEW_TYPE_HIGHLIGHTED -> ViewHolder(
                ItemHighlightedArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).root
            )
            VIEW_TYPE_NORMAL -> ViewHolder(
                ItemNormalArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).root
            )
            else -> throw IllegalArgumentException("Unexpected viewType received in RehabilitationAdapter: $viewType")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        /*when (getItemViewType(position)) {
            VIEW_TYPE_HIGHLIGHTED -> {
                (holder as HighlightedViewHolder).apply {
                    Picasso.get().load(item.urlToImage).into(binding.image)
                    binding.title.text = item.title
                    binding.publishedAt.text = item.getPublishedAgo()
                    binding.root.setOnClickListener {
                        clickHandler(item)
                    }
                }
            }
            VIEW_TYPE_NORMAL -> {
                (holder as NormalViewHolder).apply {
                    Picasso.get().load(item.urlToImage).into(binding.image)
                    binding.title.text = item.title
                    binding.publishedAt.text = item.getPublishedAgo()
                    binding.root.setOnClickListener {
                        clickHandler(item)
                    }
                }
            }
        }*/
        Timber.d("highlighted article's data: ${item.title} - ${item.getPublishedAgo()} - ${item.urlToImage}")
        Picasso.get().load(item.urlToImage).into(holder.image)
        holder.title.text = item.title
        holder.publishedAgo.text = item.getPublishedAgo()
        holder.itemView.setOnClickListener {
            clickHandler(item)
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) =
        if (position == 0) VIEW_TYPE_HIGHLIGHTED else VIEW_TYPE_NORMAL

    class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val title = rootView.findViewById<AppCompatTextView>(R.id.title)
        val publishedAgo = rootView.findViewById<AppCompatTextView>(R.id.published_ago)
        val image = rootView.findViewById<AppCompatImageView>(R.id.image)
    }

    /*class HighlightedViewHolder(val binding: ItemHighlightedArticleBinding) : RecyclerView.ViewHolder(binding.root)
    class NormalViewHolder(val binding: ItemNormalArticleBinding) : RecyclerView.ViewHolder(binding.root)*/
}