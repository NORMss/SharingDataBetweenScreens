package com.norm.mysharingdatabetweenscreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.norm.mysharingdatabetweenscreens.ui.theme.MySharingDataBetweenScreensTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySharingDataBetweenScreensTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    NavigationArgsSample()
//                    SharedViewModelSample()
//                    StatefulDependencySample()
//                    AppRoot()
                    PersistentStorageSample()
                }
            }
        }
    }
}