package com.example.rentjoy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentjoy.data.api.AuthApi
import com.example.rentjoy.data.model.AuthResponse
import com.example.rentjoy.data.model.SignInRequest
import com.example.rentjoy.data.model.SignUpRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(private val authApi: AuthApi) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = authApi.signIn(SignInRequest(email, password))
                handleAuthResponse(response)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun signUp(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                if (password != confirmPassword) {
                    _authState.value = AuthState.Error("Passwords do not match")
                    return@launch
                }
                val response = authApi.signUp(SignUpRequest(confirmPassword, email, name, password))
                handleAuthResponse(response)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "An error occurred")
            }
        }
    }

    private fun handleAuthResponse(response: Response<AuthResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { authResponse ->
                if (authResponse.token == null || authResponse.user == null) {
                    _authState.value = AuthState.Error("Invalid email or password")
                } else {
                    _authState.value = AuthState.Success(authResponse)
                }
            } ?: run {
                _authState.value = AuthState.Error("Invalid email or password")
            }
        } else {
            _authState.value = AuthState.Error("Authentication failed: ${response.code()}")
        }
    }
}

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    data class Success(val response: AuthResponse) : AuthState()
    data class Error(val message: String) : AuthState()
} 