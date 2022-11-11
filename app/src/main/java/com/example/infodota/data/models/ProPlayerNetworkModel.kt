package com.example.infodota.data.models

import com.google.gson.annotations.SerializedName

data class ProPlayerNetworkModel(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("steamid")
    val steamId: String,
    val avatar: String,
    @SerializedName("avatarmedium")
    val avatarMedium: String,
    @SerializedName("avatarfull")
    val avatarFull: String,
    @SerializedName("profileurl")
    val profileUrl: String,
    @SerializedName("personaname")
    val personaName: String,
    val cheese: Int,
    val name: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("fantasy_role")
    val fantasyRole: Int,
    @SerializedName("team_id")
    val teamId: Int,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("team_tag")
    val teamTag: String,
    @SerializedName("is_pro")
    val isPro: Boolean,
)
