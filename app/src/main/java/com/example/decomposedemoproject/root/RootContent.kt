package com.example.decomposedemoproject.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.decomposedemoproject.feature_one.presentation.content.FeatureOneScreen
import com.example.decomposedemoproject.feature_two.presentation.content.FeatureTwoScreen

@Composable
fun RootContent(component: RootComponent) {
    val childStack by component.childStack.subscribeAsState()

    Children(childStack) {
        when(val instance = it.instance){
            is RootComponent.Child.FeatureOne -> {
                FeatureOneScreen(instance.component)
            }
            is RootComponent.Child.FeatureTwo -> {
                FeatureTwoScreen(instance.component)
            }
        }
    }
}