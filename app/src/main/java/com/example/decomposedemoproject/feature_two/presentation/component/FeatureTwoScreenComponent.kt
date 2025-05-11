package com.example.decomposedemoproject.feature_two.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.decomposedemoproject.feature_two.presentation.store.FeatureTwoScreenComponentStore
import com.example.decomposedemoproject.feature_two.presentation.store.FeatureTwoScreenComponentStoreFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

interface FeatureTwoScreenComponent {
    val model: StateFlow<FeatureTwoScreenComponentStore.State>
}

class DefaultFeatureTwoScreenComponent @AssistedInject constructor(
    @Assisted("componentContext") componentContext: ComponentContext,
    @Assisted("id") id: String,
    featureTwoScreenComponentStoreFactory: FeatureTwoScreenComponentStoreFactory
): FeatureTwoScreenComponent, ComponentContext by componentContext {
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
            @Assisted("id") id: String,
        ): DefaultFeatureTwoScreenComponent
    }

    val store = featureTwoScreenComponentStoreFactory.create(id)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<FeatureTwoScreenComponentStore.State> = store.stateFlow


}