package com.norm.mysharingdatabetweenscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavigationArgsSample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1",
    ) {
        composable("screen1") {
            Screen1(
                onNavigateToScreen2 = {
                    navController.navigate("screen2/$it")
                }
            )
        }
        composable(
            route = "screen2/{my_param}",
            arguments = listOf(
                navArgument("my_param") {
                    type = NavType.StringType
                }
            )
        ) {
            val param = it.arguments?.getString("my_param") ?: ""
            Screen2(param = param)
        }
    }
}

@Composable
private fun Screen1(
    onNavigateToScreen2: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                onNavigateToScreen2("Hello, Screen2!")
            }
        ) {
            Text(
                text = "Open Screen2"
            )
        }
    }
}

@Composable
private fun Screen2(
    param: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = param
        )
    }
}