package com.example.infodota.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infodota.data.network.TeamsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val repository: TeamsRepository,
) : ViewModel() {
    private var _state = MutableStateFlow<TeamsScreenState>(TeamsScreenState.Loading)
    val state: StateFlow<TeamsScreenState> by ::_state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTeams().collect {
                if (it.isSuccessful) {
                    _state.value = TeamsScreenState.Content(it.body() ?: emptyList())
                } else {
                    Timber.e(it.errorBody().toString())
                }
            }
        }
    }
}
