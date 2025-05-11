package com.example.decomposedemoproject.feature_one.presentation.component

import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore
import kotlinx.coroutines.flow.StateFlow


interface FeatureOneScreenComponent {
    val model: StateFlow<FeatureOneScreenComponentStore.State>
}

