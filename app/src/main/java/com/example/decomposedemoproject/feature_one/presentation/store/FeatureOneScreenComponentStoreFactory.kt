package com.example.decomposedemoproject.feature_one.presentation.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.decomposedemoproject.feature_one.domain.GetFeatureOneDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeatureOneScreenComponentStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getFeatureOneDataUseCase: GetFeatureOneDataUseCase
) {

    fun create(): FeatureOneScreenComponentStore =
        object : FeatureOneScreenComponentStore,
            Store<FeatureOneScreenComponentStore.Intent, FeatureOneScreenComponentStore.State, FeatureOneScreenComponentStore.Label> by storeFactory.create(
                name = "FeatureOneScreenComponentStore",
                initialState = FeatureOneScreenComponentStore.State(
                    featureOneData = "",
                    dataLoading = false
                ),
                bootstrapper = BootstrapperImpl(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    private sealed interface Action {
        data class GetDataSuccess(val data: String) : Action
        data class GetDataFailure(val errorMessage: String) : Action
        data class GetDataLoading(val isLoading: Boolean) : Action
    }

    private sealed interface Msg {
        data class GetDataSuccess(val data: String) : Msg
        data class GetDataFailure(val errorMessage: String) : Msg
        data class GetDataLoading(val isLoading: Boolean) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                dispatch(Action.GetDataLoading(true))
                getFeatureOneDataUseCase.invoke()
                    .onSuccess {
                        dispatch(Action.GetDataSuccess(it))
                    }.onFailure {
                        dispatch(Action.GetDataFailure(it.message ?: "Не удалось загрузить данные"))
                    }
            }

        }
    }

    private class ExecutorImpl :
        CoroutineExecutor<FeatureOneScreenComponentStore.Intent, Action, FeatureOneScreenComponentStore.State, Msg, FeatureOneScreenComponentStore.Label>() {
        override fun executeIntent(
            intent: FeatureOneScreenComponentStore.Intent,
            getState: () -> FeatureOneScreenComponentStore.State
        ) {
        }

        override fun executeAction(
            action: Action,
            getState: () -> FeatureOneScreenComponentStore.State
        ) {
            when (action) {
                is Action.GetDataFailure -> {
                    dispatch(Msg.GetDataFailure(action.errorMessage))
                }

                is Action.GetDataSuccess -> {
                    dispatch(Msg.GetDataFailure(action.data))
                }

                is Action.GetDataLoading -> {
                    dispatch(Msg.GetDataLoading(action.isLoading))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<FeatureOneScreenComponentStore.State, Msg> {
        override fun FeatureOneScreenComponentStore.State.reduce(msg: Msg): FeatureOneScreenComponentStore.State =
            when (msg) {
                is Msg.GetDataFailure -> copy(featureOneData = msg.errorMessage, dataLoading = false)
                is Msg.GetDataSuccess -> copy(featureOneData = msg.data, dataLoading = false)
                is Msg.GetDataLoading -> copy(dataLoading = msg.isLoading)
            }
    }
}