package co.proexe.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.proexe.utils.mvi.IModel
import co.proexe.view.mvi.ProgramIntent
import co.proexe.view.mvi.ProgramState
import co.proexe.view.mvi.ProgramViewState
import com.example.domain.usecase.GetProgramUseCase
import com.example.domain.utils.None
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramViewModel @Inject constructor(
    private val getProgramUseCase: GetProgramUseCase
) : ViewModel(), IModel<ProgramState, ProgramIntent> {

    override val intents: Channel<ProgramIntent> = Channel(Channel.UNLIMITED)

    private val _state =
        MutableLiveData<ProgramState>().apply { value = ProgramState(ProgramViewState.Initiating) }
    override val state: LiveData<ProgramState>
        get() = _state

    init {
        viewModelScope.launch {
            intents.send(ProgramIntent.FetchProgram)
        }
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { intent ->
                when (intent) {
                    is ProgramIntent.FetchProgram -> {
                        updateState {
                            ProgramState(
                                ProgramViewState.ProgramsReceived(getProgramUseCase.execute(None))
                            )
                        }
                    }
                    is ProgramIntent.OptionsSelected -> {
                        updateState { ProgramState(ProgramViewState.OptionsSelected) }
                    }
                    is ProgramIntent.MovieClicked -> {
                        updateState { ProgramState(ProgramViewState.MovieClicked) }
                    }
                }
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: ProgramState) -> ProgramState) {
        state.value?.let { safeState ->
            _state.postValue(handler(safeState))
        }
    }
}