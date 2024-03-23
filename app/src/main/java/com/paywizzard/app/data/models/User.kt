package com.paywizzard.app.data.models

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val username: String,
    val refcode: String,
    val mobile: String,
    val email: String,
    val us_wallets: Double,
    val tpin: Int,
    val  tier:Int,
)

