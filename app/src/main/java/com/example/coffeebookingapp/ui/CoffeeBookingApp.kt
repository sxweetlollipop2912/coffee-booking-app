package com.example.coffeebookingapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coffeebookingapp.data.AppContainer
import com.example.coffeebookingapp.ui.navigation.CoffeeNavGraph
import com.example.coffeebookingapp.ui.navigation.NavRoutes
import com.example.coffeebookingapp.ui.navigation.rememberCoffeeNavController
import com.example.coffeebookingapp.ui.theme.CoffeeBookingAppTheme
import kotlinx.coroutines.launch

@Composable
fun CoffeeBookingApp(
    appContainer: AppContainer,
) {
    CoffeeBookingAppTheme {
        val coffeeNavController = rememberCoffeeNavController()
        val navBackStackEntry by coffeeNavController.navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: NavRoutes.Main.HOME.route

        CoffeeNavGraph(
            appContainer = appContainer,
            coffeeNavController = coffeeNavController,
            startDestination = currentRoute,
        )
    }
}