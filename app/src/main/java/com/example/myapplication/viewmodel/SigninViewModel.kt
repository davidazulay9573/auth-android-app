package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.HttpClient
import com.example.myapplication.model.SignInRequest
import com.example.myapplication.model.SignInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel : ViewModel() {
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    var errorMessage: String by mutableStateOf("")

    fun signIn(onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val signInRequest = SignInRequest(email, password)
                val response: SignInResponse = HttpClient.authService.signIn(signInRequest)
                val token = response.token

                if (token.isNotEmpty()) {
                    AuthState.signIn(token)
                    withContext(Dispatchers.Main) {
                        onSuccess()
                        println("User logged in successfully")
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        errorMessage = "Failed to sign in"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage = e.message ?: "Error signing in"
                }
            } finally {
                isLoading = false
            }
        }
    }
}
