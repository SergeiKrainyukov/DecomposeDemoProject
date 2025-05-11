package com.example.decomposedemoproject.feature_two.presentation.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.decomposedemoproject.feature_two.domain.GetFeatureTwoDataUseCase
import com.example.decomposedemoproject.feature_two.presentation.store.FeatureTwoScreenComponentStore.Intent
import com.example.decomposedemoproject.feature_two.presentation.store.FeatureTwoScreenComponentStore.Label
import com.example.decomposedemoproject.feature_two.presentation.store.FeatureTwoScreenComponentStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface FeatureTwoScreenComponentStore : Store<Intent, State, Label> {

    sealed interface Intent

    data class State(
        val data: String,
        val isLoading: Boolean = false
    )

    sealed interface Label {
    }
}

class FeatureTwoScreenComponentStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getFeatureTwoDataUseCase: GetFeatureTwoDataUseCase
) {

    fun create(id: String): FeatureTwoScreenComponentStore =
        object : FeatureTwoScreenComponentStore, Store<Intent, State, Label> by storeFactory.create(
            name = "FeatureTwoScreenComponentStore",
            initialState = State(
                data = ""
            ),
            bootstrapper = BootstrapperImpl(id),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        class DataLoading(val isLoading: Boolean) :
            Action

        class DataLoaded(val data: String) : Action
        class DataLoadingFailure(val errorMessage: String) : Action

    }

    private sealed interface Msg {
        class DataLoading(val isLoading: Boolean) :
            Msg

        class DataLoaded(val data: String) : Msg
        class DataLoadingFailure(val errorMessage: String) : Msg
    }

    private inner class BootstrapperImpl(
        private val id: String
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                dispatch(Action.DataLoading(true))
                getFeatureTwoDataUseCase.invoke(id)
                    .onSuccess {
                        dispatch(Action.DataLoaded(it))
                    }.onFailure {
                        dispatch(
                            Action.DataLoadingFailure(
                                it.message ?: "Не удалось загрузить данные"
                            )
                        )
                    }
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {

        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.DataLoaded -> dispatch(Msg.DataLoaded(action.data))
                is Action.DataLoading -> dispatch(Msg.DataLoading(action.isLoading))
                is Action.DataLoadingFailure -> dispatch(Msg.DataLoadingFailure(action.errorMessage))
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.DataLoaded -> copy(data = msg.data, isLoading = false)
                is Msg.DataLoading -> copy(isLoading = msg.isLoading)
                is Msg.DataLoadingFailure -> copy(data = msg.errorMessage, isLoading = false)
            }
    }
}
