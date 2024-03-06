package com.norm.mysharingdatabetweenscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.norm.mysharingdatabetweenscreens.util.PersistentViewModel1
import com.norm.mysharingdatabetweenscreens.util.PersistentViewModel2

@Composable
fun PersistentStorageSample() {
    val nacController = rememberNavController()
    NavHost(
        navController = nacController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            val viewModel = hiltViewModel<PersistentViewModel1>()

            LaunchedEffect(Unit) {
                println("Session: ${viewModel.session}")
            }

            Screen1(
                onNavigateToScreen2 = {
                    viewModel.saveSession()
                    nacController.navigate("screen2")
                }
            )
        }
        composable("screen2") {
            val viewModel = hiltViewModel<PersistentViewModel2>()

            LaunchedEffect(Unit) {
                println("Session: ${viewModel.session}")
            }

            Screen2()
        }
    }
}

@Composable
private fun Screen1(
    onNavigateToScreen2: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                onNavigateToScreen2()
            }
        ) {
            Text(
                text = "Sign in"
            )
        }
    }
}

@Composable
private fun Screen2() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Authorization successful"
        )
    }
}