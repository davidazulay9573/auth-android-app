package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.HttpClient
import com.example.myapplication.model.VerifyRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VerifyViewModel : ViewModel() {
    var code: String by mutableStateOf("")
    var userId: String? by mutableStateOf(null)
    var isLoading: Boolean by mutableStateOf(false)
    var errorMessage: String by mutableStateOf("")

    var navigateToSignIn: (() -> Unit)? = null

    fun verify() {
        isLoading = true
        errorMessage = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                userId?.let { id ->
                    val verifyRequest = VerifyRequest(code, id)
                    val response = HttpClient.authService.verify(verifyRequest)

                    withContext(Dispatchers.Main) {
                        navigateToSignIn?.invoke()
                    }
                } ?: throw Exception("User ID is required for verification")
            } catch (e: Exception) {
                errorMessage = "Error verifying code"
            } finally {
                isLoading = false
            }
        }
    }
}
