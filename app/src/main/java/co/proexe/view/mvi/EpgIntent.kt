package co.proexe.view.mvi

import co.proexe.utils.mvi.IIntent

sealed class EpgIntent : IIntent {
    object FetchProgram : EpgIntent()
}