package com.example.decomposedemoproject.feature_two.presentation.component

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

interface FeatureTwoScreenComponent {

}

class DefaultFeatureTwoScreenComponent @AssistedInject constructor(
    @Assisted("componentContext") componentContext: ComponentContext
): FeatureTwoScreenComponent, ComponentContext by componentContext {
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
        ): DefaultFeatureTwoScreenComponent
    }
}