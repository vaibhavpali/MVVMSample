package com.vaibhav.mvvmsample.data.api

import com.vaibhav.mvvmsample.AppConstants.API_KEY
import com.vaibhav.mvvmsample.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NewsService {
    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getNews(@Query("country") country: String): NewsResponse
}