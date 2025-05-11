package com.example.decomposedemoproject.feature_one.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.decomposedemoproject.feature_two.presentation.component.FeatureOneScreenComponent

@Composable
fun FeatureOneScreen(featureOneScreenComponent: FeatureOneScreenComponent) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Text("FeatureOneScreen")
    }
}