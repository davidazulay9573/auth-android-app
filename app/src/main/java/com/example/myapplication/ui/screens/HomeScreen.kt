package com.example.myapplication.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.viewmodel.AuthState

@Composable
fun HomeScreen(navController: NavController){
    if (!AuthState.isAuthenticated()){
        navController.navigate("signin")
        return
    }
    Text("home screen")
}