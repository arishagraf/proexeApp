package co.proexe.epgmvi

import co.proexe.utils.IIntent

sealed class EPGIntent : IIntent {
    object FetchProgram : EPGIntent()
}