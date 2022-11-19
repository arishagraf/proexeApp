package co.proexe.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.proexe.utils.mvi.IModel
import co.proexe.view.mvi.EpgIntent
import co.proexe.view.mvi.EpgState
import co.proexe.view.mvi.EpgViewState
import com.example.domain.usecase.GetProgramUseCase
import com.example.domain.utils.None
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpgViewModel @Inject constructor(
    private val getProgramUseCase: GetProgramUseCase
) : ViewModel(), IModel<EpgState, EpgIntent> {

    override val intents: Channel<EpgIntent> = Channel(Channel.UNLIMITED)

    private val _state =
        MutableLiveData<EpgState>().apply { value = EpgState(EpgViewState.Initiating) }
    override val state: LiveData<EpgState>
        get() = _state

    init {
        viewModelScope.launch {
            intents.send(EpgIntent.FetchProgram)
        }
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { intent ->
                when (intent) {
                    is EpgIntent.FetchProgram -> getPrograms()
                }
            }
        }
    }

    private fun getPrograms() {
        viewModelScope.launch {
            try {
                getProgramUseCase.execute(None)
                updateState { EpgState(EpgViewState.ProgramsReceived) }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: EpgState) -> EpgState) {
        state.value?.let { safeState ->
            _state.postValue(handler(safeState))
        }
    }
}