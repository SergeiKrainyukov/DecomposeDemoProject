package com.example.decomposedemoproject.feature_one.presentation.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore.Intent
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore.Label
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore.State


interface FeatureOneScreenComponentStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object OnClickNavigateFeatureTwo : Intent
    }

    data class State(
        val featureOneData: String,
        val id: String = "",
        val isLoading: Boolean = false
    )

    sealed interface Label {
        class NavigateToFeatureTwo(val id: String) : Label
    }
}