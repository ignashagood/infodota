package com.example.infodota.ui.heroes

import com.example.infodota.data.models.HeroNetworkModel

sealed class HeroesScreenState {
    object Loading : HeroesScreenState()
    data class Content(val heroes: List<HeroNetworkModel>) : HeroesScreenState()
}
