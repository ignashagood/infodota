package com.example.infodota.data.network

import com.example.infodota.data.models.HeroNetworkModel
import com.example.infodota.data.models.ProPlayerNetworkModel
import com.example.infodota.data.models.TeamNetworkModel
import retrofit2.Response
import retrofit2.http.GET

interface OpenDotaApi {

    @GET("./heroStats")
    suspend fun getAllHeroes(): Response<List<HeroNetworkModel>>

    @GET("./teams")
    suspend fun getAllTeams(): Response<List<TeamNetworkModel>>

    @GET("./proPlayers")
    suspend fun getAllPlayers(): Response<List<ProPlayerNetworkModel>>
}
