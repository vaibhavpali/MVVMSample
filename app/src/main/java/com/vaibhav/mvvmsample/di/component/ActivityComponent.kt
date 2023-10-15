package com.vaibhav.mvvmsample.di.component

import com.vaibhav.mvvmsample.di.ActivityScope
import com.vaibhav.mvvmsample.di.module.ActivityModule
import com.vaibhav.mvvmsample.di.module.ApplicationModule
import com.vaibhav.mvvmsample.ui.NewsActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(newsActivity: NewsActivity)
}