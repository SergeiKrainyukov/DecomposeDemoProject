package com.example.decomposedemoproject.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.example.decomposedemoproject.feature_two.presentation.component.DefaultFeatureOneScreenComponent
import com.example.decomposedemoproject.feature_two.presentation.component.FeatureOneScreenComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class FeatureOne(val component: FeatureOneScreenComponent) : Child
        class FeatureTwo() : Child
    }
}

class DefaultRootComponent @AssistedInject constructor(
    @Assisted("componentContext") private val componentContext: ComponentContext,
    private val featureOneScreenComponentFactory: DefaultFeatureOneScreenComponent.Factory,
): RootComponent, ComponentContext by componentContext {
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
        ): DefaultRootComponent
    }

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.FeatureOne,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child = when (config) {
        Config.FeatureOne -> {
            RootComponent.Child.FeatureOne(featureOneScreenComponentFactory.create(componentContext))
        }
        Config.FeatureTwo -> {
            RootComponent.Child.FeatureTwo()
        }
    }

    @Serializable
    private sealed class Config {

        @Serializable
        data object FeatureOne : Config()

        @Serializable
        data object FeatureTwo : Config()

    }
}