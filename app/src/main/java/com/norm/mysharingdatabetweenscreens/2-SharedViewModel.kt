package com.norm.mysharingdatabetweenscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.norm.mysharingdatabetweenscreens.util.SharedViewModel

@Composable
fun SharedViewModelSample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        navigation(
            startDestination = "personal_details",
            route = "onboarding",
        ) {
            composable("personal_details") { entry ->
                val viewModel = entry.sharedViewModel<SharedViewModel>(navController)
                val state by viewModel.sharedState.collectAsStateWithLifecycle()

                PersonalDetailsScreen(
                    sharedState = state,
                    onNavigate = {
                        viewModel.updateState()
                        navController.navigate("terms_and_conditions")
                    }
                )
            }
            composable("terms_and_conditions") { entry ->
                val viewModel = entry.sharedViewModel<SharedViewModel>(navController)
                val state by viewModel.sharedState.collectAsStateWithLifecycle()

                TermsAndConditionalsScreen(
                    sharedState = state,
                    onOnboardingFinished = {
                        navController.navigate(route = "other_screen") {
                            popUpTo("onboarding") {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable("other_screen") {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "This is other screen"
                    )
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

@Composable
private fun PersonalDetailsScreen(
    sharedState: Int,
    onNavigate: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = onNavigate
        ) {
            Text(
                text = "Crate new state"
            )
        }
    }
}

@Composable
private fun TermsAndConditionalsScreen(
    sharedState: Int,
    onOnboardingFinished: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = onOnboardingFinished
        ) {
            Text(
                text = "Count State $sharedState"
            )
        }
    }
}