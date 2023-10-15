package com.vaibhav.mvvmsample.data

import com.vaibhav.mvvmsample.data.api.NewsService
import com.vaibhav.mvvmsample.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsService: NewsService) {
    fun getNews(country: String): Flow<List<Article>> {
        return flow {
            emit(newsService.getNews(country))
        }.map { it.articles }
    }
}