package com.example.decomposedemoproject.feature_two.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.decomposedemoproject.feature_two.presentation.component.FeatureTwoScreenComponent

@Composable
fun FeatureTwoScreen(featureTwoScreenComponent: FeatureTwoScreenComponent) {
    Box(
        Modifier.fillMaxSize()
    ) { 
        Text("FeatureTwoScreen")
    }
}