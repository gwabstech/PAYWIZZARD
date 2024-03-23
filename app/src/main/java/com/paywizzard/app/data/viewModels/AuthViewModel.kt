package com.paywizzard.app.data.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.paywizzard.app.data.models.ForgetPasswordState
import com.paywizzard.app.data.models.ForgotPasswordRequest
import com.paywizzard.app.data.models.LoginRequest
import com.paywizzard.app.data.models.LoginState
import com.paywizzard.app.data.models.LogoutState
import com.paywizzard.app.data.models.RegisterRequest
import com.paywizzard.app.data.models.RegisterState
import com.paywizzard.app.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel(private val apiService: ApiService) : ViewModel() {


    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _logoutState = MutableStateFlow(LogoutState())
    val logoutState: StateFlow<LogoutState> = _logoutState.asStateFlow()

    private val _forgotPasswordState = MutableStateFlow(ForgetPasswordState())
    val forgotPasswordState: StateFlow<ForgetPasswordState> = _forgotPasswordState.asStateFlow()



    fun login(
        email: String,
        password: String

    ) {

        _loginState.value = LoginState.Idle
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = apiService.login(
                    LoginRequest(email, password)

                )
                _loginState.value = LoginState.Success(response.user, response.token)

            } catch (error: HttpException) {
                val errorMessage = try {
                    val errorBody =
                        error.response()?.errorBody() ?: return@launch  // Handle empty response
                    val errorString = errorBody.string()
                    val gson = Gson()
                    val errorMap = gson.fromJson(errorString, Map::class.java) as Map<*, *>
                    errorMap["message"] as? String
                        ?: "Unknown error" // Use default if message missing
                } catch (e: Exception) {
                    "Error parsing error response" // Handle parsing exceptions
                }

                _loginState.value = LoginState.Error(errorMessage)

            }
        }
    }

    fun logout(token: String) {
        _logoutState.value = LogoutState.Idle
        viewModelScope.launch {
            _logoutState.value = LogoutState.Loading

            try {
                val response = apiService.logout(token)
                _logoutState.value = LogoutState.Success(response.message)

            } catch (error: HttpException) {
                val errorMessage = try {
                    val errorBody =
                        error.response()?.errorBody() ?: return@launch  // Handle empty response
                    val errorString = errorBody.string()
                    val gson = Gson()
                    val errorMap = gson.fromJson(errorString, Map::class.java) as Map<*, *>
                    errorMap["message"] as? String
                        ?: "Unknown error" // Use default if message missing
                } catch (e: Exception) {
                    "Error parsing error response" // Handle parsing exceptions
                }

                _logoutState.value = LogoutState.Error(errorMessage)
            }

        }
    }


    fun forgotPassword(email: String) {
        _forgotPasswordState.value = ForgetPasswordState.Idle
        viewModelScope.launch {
            _forgotPasswordState.value = ForgetPasswordState.Loading
            try {
                val response = apiService.forgetPassword(ForgotPasswordRequest(email))
                _forgotPasswordState.value = ForgetPasswordState.Success(response.message)
            } catch (error: HttpException) {
                val errorMessage = try {
                    val errorBody =
                        error.response()?.errorBody() ?: return@launch  // Handle empty response
                    val errorString = errorBody.string()
                    val gson = Gson()
                    val errorMap = gson.fromJson(errorString, Map::class.java) as Map<*, *>
                    errorMap["message"] as? String
                        ?: "Unknown error" // Use default if message missing
                } catch (e: Exception) {
                    "Error parsing error response" // Handle parsing exceptions
                }

                _forgotPasswordState.value = ForgetPasswordState.Error(errorMessage)
            }
        }

    }

    class Factory(private val apiService: ApiService) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("unchecked_cast")
                return AuthViewModel(apiService) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}