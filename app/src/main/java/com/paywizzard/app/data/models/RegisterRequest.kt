package com.paywizzard.app.data.models

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val mobile: String,
    val username: String,
    val refby: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)