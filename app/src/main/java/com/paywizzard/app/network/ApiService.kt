package com.paywizzard.app.network

import com.paywizzard.app.data.models.ForgortPasswordResponse
import com.paywizzard.app.data.models.ForgotPasswordRequest
import com.paywizzard.app.data.models.LoginRequest
import com.paywizzard.app.data.models.LoginResponse
import com.paywizzard.app.data.models.LogoutResponse
import com.paywizzard.app.data.models.RegisterRequest
import com.paywizzard.app.data.models.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("api/logout")
    suspend fun logout(
        @Header("Authorization") authorization: String
    ): LogoutResponse // Define the expected response structure

    @POST("api/forgetpassword")
    suspend fun forgetPassword(
        @Body forgotPasswordRequest: ForgotPasswordRequest,
    ): ForgortPasswordResponse

    @POST("api/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse
}

