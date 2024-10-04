package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.viewmodel.VerifyViewModel

@Composable
fun VerifyScreen(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val userId = backStackEntry?.arguments?.getString("userId")
    val viewModel: VerifyViewModel = viewModel()

    viewModel.userId = userId

    viewModel.navigateToSignIn = {
        navController.navigate("signin")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Check your email", fontSize = 20.sp)

        TextField(
            value = viewModel.code,
            onValueChange = { viewModel.code = it },
            label = { Text("Code") },
            placeholder = { Text("Enter your code") }
        )

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = { viewModel.verify() }) {
                Text("Send")
            }
        }

        if (viewModel.errorMessage.isNotEmpty()) {
            Text(text = viewModel.errorMessage, color = Color.Red)
        }
    }
}
