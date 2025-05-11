package com.example.decomposedemoproject.feature_one.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.decomposedemoproject.extensions.componentScope
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStoreFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultFeatureOneScreenComponent @AssistedInject constructor(
    @Assisted("componentContext") componentContext: ComponentContext,
    @Assisted("navigateToFeatureTwo") private val navigateToFeatureTwo: (id: String) -> Unit,
    storeFactory: FeatureOneScreenComponentStoreFactory
) : FeatureOneScreenComponent, ComponentContext by componentContext {
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
            @Assisted("navigateToFeatureTwo") navigateToFeatureTwo: (id: String) -> Unit,
        ): DefaultFeatureOneScreenComponent
    }

    private val scope = componentScope()

    private val store = storeFactory.create()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is FeatureOneScreenComponentStore.Label.NavigateToFeatureTwo -> navigateToFeatureTwo.invoke(
                        it.id
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<FeatureOneScreenComponentStore.State> = store.stateFlow

    override fun onClickNavigateFeatureTwo() {
        store.accept(FeatureOneScreenComponentStore.Intent.OnClickNavigateFeatureTwo)
    }
}