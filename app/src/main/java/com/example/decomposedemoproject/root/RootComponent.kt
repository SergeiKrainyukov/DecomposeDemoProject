package com.example.decomposedemoproject.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.decomposedemoproject.feature_one.presentation.component.DefaultFeatureOneScreenComponent
import com.example.decomposedemoproject.feature_one.presentation.component.FeatureOneScreenComponent
import com.example.decomposedemoproject.feature_two.presentation.component.DefaultFeatureTwoScreenComponent
import com.example.decomposedemoproject.feature_two.presentation.component.FeatureTwoScreenComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class FeatureOne(val component: FeatureOneScreenComponent) : Child
        class FeatureTwo(val component: FeatureTwoScreenComponent) : Child
    }
}

class DefaultRootComponent @AssistedInject constructor(
    @Assisted("componentContext") private val componentContext: ComponentContext,
    private val featureOneScreenComponentFactory: DefaultFeatureOneScreenComponent.Factory,
    private val featureTwoScreenComponentFactory: DefaultFeatureTwoScreenComponent.Factory,
) : RootComponent, ComponentContext by componentContext {
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
            RootComponent.Child.FeatureOne(
                featureOneScreenComponentFactory.create(
                    componentContext = componentContext,
                    navigateToFeatureTwo = {
                        navigation.push(Config.FeatureTwo(it))
                    })
            )
        }

        is Config.FeatureTwo -> {
            RootComponent.Child.FeatureTwo(
                featureTwoScreenComponentFactory.create(
                    componentContext = componentContext,
                    id = config.id
                )
            )
        }
    }

    @Serializable
    private sealed class Config {

        @Serializable
        data object FeatureOne : Config()

        @Serializable
        data class FeatureTwo(
            val id: String
        ) : Config()

    }
}