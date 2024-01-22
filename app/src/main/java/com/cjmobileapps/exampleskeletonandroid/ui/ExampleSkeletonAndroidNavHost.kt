package com.cjmobileapps.exampleskeletonandroid.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cjmobileapps.exampleskeletonandroid.ui.detail.DetailUi
import com.cjmobileapps.exampleskeletonandroid.ui.detail.viewmodel.DetailViewModel
import com.cjmobileapps.exampleskeletonandroid.ui.main.MainUi
import com.cjmobileapps.exampleskeletonandroid.ui.main.viewmodel.MainViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavItem.Main.navRoute) {
        composable(NavItem.Main.navRoute) {
            val mainViewModel: MainViewModel = hiltViewModel<MainViewModel>()

            MainUi(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }

        composable(
            NavItem.Detail.navRoute,
            arguments = NavItem.Detail.arguments
        ) {
            val detailViewModel: DetailViewModel = hiltViewModel<DetailViewModel>()
            DetailUi(detailViewModel = detailViewModel)
        }
    }
}

sealed class NavItem(
    val navRoute: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Main : NavItem(navRoute = "nav_main")

    data object Detail : NavItem(
        navRoute = "nav_detail/{postId}",
        arguments = listOf(
            navArgument("postId") { type = NavType.StringType },
        )
    ) {

        fun getNavRouteWithArguments(postId: String): String {
            return "nav_detail/$postId"
        }
    }
}
