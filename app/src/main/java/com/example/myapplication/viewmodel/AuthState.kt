package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AuthState {
    var token: String? by mutableStateOf(null)
        private set

    fun signIn(token: String) { AuthState.token = token }
    fun signOut() { token = null }
    fun isAuthenticated(): Boolean = token != null
}
