package com.vaibhav.mvvmsample.di.component

import android.content.Context
import com.vaibhav.mvvmsample.NewsApplication
import com.vaibhav.mvvmsample.data.NewsRepository
import com.vaibhav.mvvmsample.data.api.NewsService
import com.vaibhav.mvvmsample.di.ApplicationContext
import com.vaibhav.mvvmsample.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(newsApplication: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNewsService(): NewsService

    fun getNewsRepository(): NewsRepository
}