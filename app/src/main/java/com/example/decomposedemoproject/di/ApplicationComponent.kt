package com.example.decomposedemoproject.di

import com.example.decomposedemoproject.root.RootActivity
import dagger.Component

@ApplicationScope
@Component(
    modules = [PresentationModule::class]
)
interface ApplicationComponent {
    fun inject(activity: RootActivity)
}