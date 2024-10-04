package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.HttpClient
import com.example.myapplication.model.SignupRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel : ViewModel() {
    var name: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    var errorMessage: String by mutableStateOf("")
    var userId: String? by mutableStateOf("")
    var navigateToVerify: ((String) -> Unit)? = null

    fun signUp() {
        isLoading = true
        errorMessage = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val signupRequest = SignupRequest(name, email, password)
                val response = HttpClient.authService.signUp(signupRequest)

                userId = response.userId

                withContext(Dispatchers.Main) {
                        navigateToVerify?.invoke(userId!!)
                }
            } catch (e: Exception) {
                errorMessage = e.message ?: "Error signing up"
            } finally {
                isLoading = false
            }
        }
    }
}
