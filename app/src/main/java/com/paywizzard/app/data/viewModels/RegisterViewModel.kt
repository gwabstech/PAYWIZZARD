package com.paywizzard.app.data.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.paywizzard.app.data.models.RegisterRequest
import com.paywizzard.app.data.models.RegisterState
import com.paywizzard.app.data.models.User
import com.paywizzard.app.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val apiService: ApiService) :
    ViewModel() {


    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _refCode = MutableLiveData<String>()
    val refCode: LiveData<String> = _refCode

    fun setRefCode(refCode: String = "admin") {
        _refCode.value = refCode
    }

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun setLastName(lastName: String) {
        _lastName.value = lastName
    }

    fun setFirstName(firstName: String) {
        _firstName.value = firstName
    }

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }


    fun setConfirmPassword(value: String) {
        _confirmPassword.value = value
    }


    fun setUsername(value: String) {
        _username.value = value
    }


    fun initializeViewModelData(){
        setPhoneNumber("")
        setFirstName("")
        setLastName("")
        setUsername("")
        setEmail("")
        setPassword("")
        setConfirmPassword("")
        setRefCode("admin")
    }

    fun register() {
        _registerState.value = RegisterState.Idle
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                val response = apiService.register(
                    RegisterRequest(
                        _firstName.value!!,
                        _lastName.value!!,
                        _phoneNumber.value!!,
                        _username.value!!,
                        _refCode.value!!,
                        _email.value!!,
                        _password.value!!,
                        _confirmPassword.value!!
                    )
                )
                _registerState.value = RegisterState.Success(response.user, response.message)
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

                _registerState.value = RegisterState.Error(errorMessage)
            }
        }

    }

    class Factory(private val apiService: ApiService) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                @Suppress("unchecked_cast")
                return RegisterViewModel(apiService) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    /*
    fun validateRegistrationData(): Boolean {
        var isValid = true

        // Check for empty fields
        if (email.value.isNullOrEmpty()) {
            isValid = false
            // Provide user feedback, for example:
            displayError("Email field is required")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isValid = false
            displayError("Invalid email format")
        }

        if (password.value.isNullOrEmpty()) {
            isValid = false
            displayError("Password field is required")
        } else if (password.value.length < 8) { // Example: require a minimum of 8 characters
            isValid = false
            displayError("Password must be at least 8 characters long")
        }

        if (username.value.isNullOrEmpty()) {
            isValid = false
            displayError("Username field is required")
        }

        // ... Add similar validations for firstName, lastName, and phoneNumber


        return isValid
    }

     */
}