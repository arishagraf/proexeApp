package co.proexe.view.mvi

import co.proexe.utils.mvi.IIntent

sealed class ProgramIntent : IIntent {
    object FetchProgram : ProgramIntent()
    object OptionsSelected : ProgramIntent()
    object MovieClicked : ProgramIntent()
}