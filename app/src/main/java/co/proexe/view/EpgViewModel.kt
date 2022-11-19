package co.proexe.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.proexe.epgmvi.EPGIntent
import co.proexe.epgmvi.EPGState
import co.proexe.epgmvi.EPGViewState
import co.proexe.utils.IModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class EpgViewModel : ViewModel(), IModel<EPGState, EPGIntent> {

    override val intents: Channel<EPGIntent> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData(EPGState(EPGViewState.Loading))
    override val state: LiveData<EPGState>
        get() = _state

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { intent ->
                when (intent) {
                    is EPGIntent.FetchProgram -> {
                        getProgram()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun getProgram() {

    }
}