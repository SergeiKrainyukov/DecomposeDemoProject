package com.example.decomposedemoproject.feature_one.presentation.component

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

interface FeatureOneScreenComponent {

}

class DefaultFeatureOneScreenComponent @AssistedInject constructor(
    @Assisted("componentContext") componentContext: ComponentContext
): FeatureOneScreenComponent, ComponentContext by componentContext {
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
        ): DefaultFeatureOneScreenComponent
    }
}