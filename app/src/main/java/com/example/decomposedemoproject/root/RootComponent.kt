package com.example.decomposedemoproject.root

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

interface RootComponent {
}

class DefaultRootComponent @AssistedInject constructor(
    @Assisted("componentContext") private val componentContext: ComponentContext
): RootComponent, ComponentContext by componentContext {
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
        ): DefaultRootComponent
    }
}