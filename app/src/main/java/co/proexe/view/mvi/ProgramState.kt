package co.proexe.view.mvi

import co.proexe.utils.mvi.IState
import com.example.domain.model.ProgramModel

data class ProgramState(
    val programViewState: ProgramViewState
) : IState

sealed class ProgramViewState : IState {
    object Initiating : ProgramViewState()
    data class ProgramsReceived(val programList: List<ProgramModel>) : ProgramViewState()
    object OptionsSelected : ProgramViewState()
    object MovieClicked : ProgramViewState()
}