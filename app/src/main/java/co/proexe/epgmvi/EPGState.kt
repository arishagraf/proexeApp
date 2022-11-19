package co.proexe.epgmvi

import co.proexe.utils.IState

data class EPGState(
    val epgViewState: EPGViewState
) : IState

sealed class EPGViewState : IState {
    object Loading : EPGViewState()
}