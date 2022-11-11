package com.example.infodota.data.models

import com.google.gson.annotations.SerializedName

data class TeamNetworkModel(
    @SerializedName("team_id")
    val teamId: Int,
    val rating: Float,
    val wins: Int,
    val losses: Int,
    val name: String,
    val tag: String,
    @SerializedName("logo_url")
    val logo: String
)
