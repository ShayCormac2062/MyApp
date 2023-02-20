package com.example.myapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapp.ui.screen.item.ItemScreen
import com.example.myapp.ui.screen.list.ListScreen
import com.example.myapp.ui.screen.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    val viewModel: MainViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.ListScreen.route) {
            ListScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(route = Screen.ItemScreen.route) {
            ItemScreen(
                viewModel,
                navController
            )
        }
    }
}
