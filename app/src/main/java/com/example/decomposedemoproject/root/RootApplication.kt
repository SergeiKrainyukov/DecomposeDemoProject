package com.example.decomposedemoproject.root

import android.app.Application
import com.example.decomposedemoproject.di.ApplicationComponent
import com.example.decomposedemoproject.di.DaggerApplicationComponent

class RootApplication : Application() {
    private var _component: ApplicationComponent? = null
    val component
        get() = _component ?: throw RuntimeException("ApplicationComponent not initialized")

    override fun onCreate() {
        super.onCreate()
        _component = DaggerApplicationComponent.create()
    }
}