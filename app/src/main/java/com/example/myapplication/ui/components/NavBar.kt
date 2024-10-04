package com.example.myapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.viewmodel.AuthState

@Composable
fun NavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        if (AuthState.isAuthenticated()) {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = currentRoute == "home",
                onClick = { navController.navigate("home") }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Filled.ExitToApp, contentDescription = "Sign Out") },
                label = { Text("Sign Out") },
                selected = false,
                onClick = {
                    AuthState.signOut()
                }
            )
        } else {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Build, contentDescription = "Sign Up") },
                label = { Text("Sign Up") },
                selected = currentRoute == "signup",
                onClick = { navController.navigate("signup") }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Person, contentDescription = "Sign In") },
                label = { Text("Sign In") },
                selected = currentRoute == "signin",
                onClick = { navController.navigate("signin") }
            )
        }
    }
}
