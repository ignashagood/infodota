package com.example.infodota.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.infodota.data.models.ProPlayerNetworkModel
import com.example.infodota.data.network.PlayersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class TeamDetailsViewModel @AssistedInject constructor(
    private val playersRepository: PlayersRepository,
    @Assisted
    private val teamId: Int,
) : ViewModel() {

    @AssistedFactory
    interface TeamDetailsViewModelFactory {
        fun create(teamId: Int): TeamDetailsViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: TeamDetailsViewModelFactory,
            teamId: Int,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(teamId) as T
            }
        }
    }

    private var _state = MutableStateFlow<TeamDetailsState>(TeamDetailsState.Loading)
    val state: StateFlow<TeamDetailsState> by ::_state

    init {
        getTeamPlayers(teamId = teamId)
    }

    private fun getTeamPlayers(teamId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            playersRepository.getAllPlayers().collect {
                if (it.isSuccessful) {
                    val teamPlayers = it.body()?.filter { player -> player.teamId == teamId }
                    _state.value = TeamDetailsState.Content(teamPlayers ?: emptyList())
                } else {
                    Timber.e(it.errorBody().toString())
                }
            }
        }
    }
}
