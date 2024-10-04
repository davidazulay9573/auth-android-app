package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.components.NavBar
import com.example.myapplication.ui.screens.SignUpScreen
import com.example.myapplication.ui.screens.SignInScreen
import com.example.myapplication.ui.screens.VerifyScreen
import com.example.myapplication.ui.screens.HomeScreen

@Composable
fun App() {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 38.dp)
    ) {
        NavHost(navController, startDestination = "home", Modifier.weight(1f)) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("signup") {
                SignUpScreen(navController)
            }
            composable("signin") {
                SignInScreen(navController)
            }
            composable("verify/{userId}") {
                VerifyScreen(navController)
            }
        }
        NavBar(navController)
    }
}
