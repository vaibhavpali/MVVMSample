package com.vaibhav.mvvmsample

import android.app.Application
import com.vaibhav.mvvmsample.di.component.ApplicationComponent
import com.vaibhav.mvvmsample.di.component.DaggerApplicationComponent
import com.vaibhav.mvvmsample.di.module.ApplicationModule

class NewsApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}