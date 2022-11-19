package co.proexe.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.proexe.utils.mvi.IModel
import co.proexe.view.mvi.*
import com.example.domain.usecase.GetProgramUseCase
import com.example.domain.usecase.GetTabsUseCase
import com.example.domain.utils.None
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTabsUseCase: GetTabsUseCase
) : ViewModel(), IModel<MainState, MainIntent> {

    override val intents: Channel<MainIntent> = Channel(Channel.UNLIMITED)

    private val _state =
        MutableLiveData<MainState>().apply { value = MainState(MainViewState.Initiating) }
    override val state: LiveData<MainState>
        get() = _state

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { intent ->
                when (intent) {
                    is MainIntent.SetUpTabLayout -> getTabs()
                }
            }
        }
    }

    private fun getTabs() {
        viewModelScope.launch {
            try {
                val tabs = getTabsUseCase.execute(None)
                updateState { MainState(MainViewState.SetUpTabs(tabs)) }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: MainState) -> MainState) {
        state.value?.let { safeState ->
            _state.postValue(handler(safeState))
        }
    }
}