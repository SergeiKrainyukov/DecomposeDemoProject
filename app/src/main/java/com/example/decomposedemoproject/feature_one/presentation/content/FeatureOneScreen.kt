package com.example.decomposedemoproject.feature_one.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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

    FeatureOneScreenContent(
        state = state,
        onClickNavigateFeatureTwo = featureOneScreenComponent::onClickNavigateFeatureTwo
    )

}

@Composable
private fun FeatureOneScreenContent(
    state: FeatureOneScreenComponentStore.State,
    onClickNavigateFeatureTwo: () -> Unit
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(state.featureOneData)
                Button(onClick = onClickNavigateFeatureTwo) {
                    Text("Go to Feature Two")
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
private fun FeatureOneScreenContentPreview() {
    FeatureOneScreenContent(
        state = FeatureOneScreenComponentStore.State(
            featureOneData = "Feature One",
            isLoading = false
        ),
        onClickNavigateFeatureTwo = {}
    )
}