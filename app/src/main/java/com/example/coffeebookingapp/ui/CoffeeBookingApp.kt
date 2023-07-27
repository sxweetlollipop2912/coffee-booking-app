package com.example.coffeebookingapp.ui

import androidx.compose.runtime.Composable
import com.example.coffeebookingapp.data.AppContainer
import com.example.coffeebookingapp.ui.navigation.CoffeeNavGraph
import com.example.coffeebookingapp.ui.navigation.NavRoutes
import com.example.coffeebookingapp.ui.navigation.rememberCoffeeNavController
import com.example.coffeebookingapp.ui.theme.CoffeeBookingAppTheme

@Composable
fun CoffeeBookingApp(
    appContainer: AppContainer,
) {
    CoffeeBookingAppTheme {
        val coffeeNavController = rememberCoffeeNavController()

        CoffeeNavGraph(
            appContainer = appContainer,
            coffeeNavController = coffeeNavController,
            startDestination = NavRoutes.MainBottomBar.HOME.route,
        )
    }
}