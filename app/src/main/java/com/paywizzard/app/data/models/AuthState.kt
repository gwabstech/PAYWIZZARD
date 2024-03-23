package com.paywizzard.app.data.models

open class LoginState {
    object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val user: User, val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

open class LogoutState{
    object Idle : LogoutState()
    data object Loading : LogoutState()
    data class Success(val message: String):LogoutState()
    data class Error(val message: String) : LogoutState()
}

open class ForgetPasswordState{
    object Idle : ForgetPasswordState()
    data object Loading : ForgetPasswordState()
    data class Success(val message: String):ForgetPasswordState()
    data class Error(val message: String) : ForgetPasswordState()
}
open class RegisterState {
    object Idle : RegisterState()
    data object Loading : RegisterState()
    data class Success(val user: User, val massage: String) : RegisterState()
    data class Error(val message: String) : RegisterState()
}
