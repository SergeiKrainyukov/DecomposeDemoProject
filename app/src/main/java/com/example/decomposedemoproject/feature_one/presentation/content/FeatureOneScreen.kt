package com.example.decomposedemoproject.feature_one.presentation.content

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
import com.example.decomposedemoproject.feature_one.presentation.component.FeatureOneScreenComponent
import com.example.decomposedemoproject.feature_one.presentation.store.FeatureOneScreenComponentStore

@Composable
fun FeatureOneScreen(featureOneScreenComponent: FeatureOneScreenComponent) {

    val state by featureOneScreenComponent.model.collectAsState()

    FeatureOneScreenContent(state)

}

@Composable
private fun FeatureOneScreenContent(
    state: FeatureOneScreenComponentStore.State
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        if (state.dataLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else
            Text(state.featureOneData, modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
private fun FeatureOneScreenContentPreview() {
    FeatureOneScreenContent(
        state = FeatureOneScreenComponentStore.State(
            featureOneData = "featureOneData",
            dataLoading = false
        )
    )
}