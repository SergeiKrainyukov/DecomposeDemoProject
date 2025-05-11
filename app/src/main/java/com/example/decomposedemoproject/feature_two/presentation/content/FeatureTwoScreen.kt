package com.example.decomposedemoproject.feature_two.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.decomposedemoproject.feature_two.presentation.component.FeatureTwoScreenComponent
import com.example.decomposedemoproject.feature_two.presentation.store.FeatureTwoScreenComponentStore

@Composable
fun FeatureTwoScreen(featureTwoScreenComponent: FeatureTwoScreenComponent) {
    val state by featureTwoScreenComponent.model.collectAsState()
    FeatureTwoScreenContent(state)
}

@Composable
private fun FeatureTwoScreenContent(
    state: FeatureTwoScreenComponentStore.State
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else
            Text(state.data, modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
private fun FeatureTwoScreenPreview() {
    FeatureTwoScreenContent(
        state = FeatureTwoScreenComponentStore.State(
            data = "Feature Two",
            isLoading = false
        )
    )
}