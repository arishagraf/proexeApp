package co.proexe.view.mvi

import co.proexe.utils.mvi.IState

data class MainState(
    val mainViewState: MainViewState
) : IState

sealed class MainViewState : IState {
    object Initiating : MainViewState()
    data class SetUpTabs(val category: List<String>) : MainViewState()
}