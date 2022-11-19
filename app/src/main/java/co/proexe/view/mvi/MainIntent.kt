package co.proexe.view.mvi

import co.proexe.utils.mvi.IIntent

sealed class MainIntent : IIntent {
    object SetUpTabLayout : MainIntent()
}