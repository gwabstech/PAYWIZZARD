package com.paywizzard.app.data.models

data class LoginResponse(
    val user: User,
    val token: String
)
