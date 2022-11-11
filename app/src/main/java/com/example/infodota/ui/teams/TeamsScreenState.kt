package com.example.infodota.ui.teams

import com.example.infodota.data.models.TeamNetworkModel

sealed class TeamsScreenState {
    data class Content(val teams: List<TeamNetworkModel>) : TeamsScreenState()
    object Loading : TeamsScreenState()
}
