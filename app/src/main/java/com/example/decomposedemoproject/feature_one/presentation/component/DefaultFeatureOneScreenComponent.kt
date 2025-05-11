package com.example.decomposedemoproject.feature_one.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStoreFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class DefaultFeatureOneScreenComponent @AssistedInject constructor(
    @Assisted("componentContext") componentContext: ComponentContext,
    storeFactory: FeatureOneScreenComponentStoreFactory
): FeatureOneScreenComponent, ComponentContext by componentContext {
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
        ): DefaultFeatureOneScreenComponent
    }

    private val store = storeFactory.create()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<FeatureOneScreenComponentStore.State> = store.stateFlow
}