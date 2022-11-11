package com.example.infodota.data.models

import com.google.gson.annotations.SerializedName

data class HeroNetworkModel(
    val id: Int,
    val name: String,
    val localized_name: String,
    @SerializedName("img")
    val image: String,
    val icon: String,
    val primary_attr: String,
    val attack_type: String,
    val roles: List<String>,
    val base_health: Int,
    val base_health_regen: Float,
    val base_mana: Int,
    val base_mana_regen: Float,
    val base_armor: Float,
    val base_mr: Float,
    val base_str: Int,
    val base_agi: Int,
    val base_int: Int,
    val str_gain: Float,
    val agi_gain: Float,
    val int_gain: Float,
    val attack_range: Int,
    val move_speed: Int,
)
