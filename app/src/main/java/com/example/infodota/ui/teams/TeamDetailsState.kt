package com.example.infodota.ui.teams

import com.example.infodota.data.models.ProPlayerNetworkModel

sealed class TeamDetailsState {
    data class Content(val players: List<ProPlayerNetworkModel>) : TeamDetailsState()
    object Loading : TeamDetailsState()
}
