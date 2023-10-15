package com.vaibhav.mvvmsample.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vaibhav.mvvmsample.data.NewsRepository
import com.vaibhav.mvvmsample.data.model.Article
import com.vaibhav.mvvmsample.di.ActivityContext
import com.vaibhav.mvvmsample.ui.NewsAdapter
import com.vaibhav.mvvmsample.ui.NewsViewModel
import com.vaibhav.mvvmsample.ui.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsViewModel(repository: NewsRepository): NewsViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(NewsViewModel::class) { NewsViewModel(repository) })[NewsViewModel::class.java]
    }

    @Provides
    fun provideNewsAdapter() = NewsAdapter(ArrayList())
}