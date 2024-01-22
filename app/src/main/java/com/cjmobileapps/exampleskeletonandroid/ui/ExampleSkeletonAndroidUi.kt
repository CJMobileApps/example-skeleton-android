package com.cjmobileapps.exampleskeletonandroid.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cjmobileapps.exampleskeletonandroid.ui.theme.ExampleSkeletonAndroidTheme


@Composable
fun ExampleSkeletonAndroidUi() {

    val navController = rememberNavController()

    ExampleSkeletonAndroidTheme {
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph(
                    navController = navController
                )
            }
        }
    }
}
