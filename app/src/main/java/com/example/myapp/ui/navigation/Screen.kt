package com.example.myapp.ui.navigation

sealed class Screen(val route: String) {
    object ListScreen : Screen(route = "list_screen")
    object ItemScreen : Screen(route = "item_screen")
}
