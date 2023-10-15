package com.vaibhav.mvvmsample.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vaibhav.mvvmsample.data.model.Article
import com.vaibhav.mvvmsample.databinding.NewsItemBinding

class NewsAdapter(private val articles: ArrayList<Article>) :
    RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.imageUrl)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    fun addData(list: List<Article>) {
        articles.addAll(list)
    }
}