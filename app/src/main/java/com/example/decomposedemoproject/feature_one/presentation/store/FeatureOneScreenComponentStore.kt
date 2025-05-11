package com.example.decomposedemoproject.feature_one.presentation.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore.Intent
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore.Label
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore.State


interface FeatureOneScreenComponentStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State(
        val featureOneData: String,
        val dataLoading: Boolean
    )

    sealed interface Label {
    }
}