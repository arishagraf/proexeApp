package co.proexe.view.mvi

import co.proexe.utils.mvi.IState

data class EpgState(
    val epgViewState: EpgViewState
) : IState

sealed class EpgViewState : IState {
    object Initiating : EpgViewState()
    object ProgramsReceived : EpgViewState()
}