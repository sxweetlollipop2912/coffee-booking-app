package com.example.coffeebookingapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
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
        val navBackStackEntry by coffeeNavController.navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: NavRoutes.Main.HOME.route

        CoffeeNavGraph(
            appContainer = appContainer,
            coffeeNavController = coffeeNavController,
            startDestination = currentRoute,
        )

//        val detailsViewModel: DetailsViewModel = viewModel(
//            factory = DetailsViewModel.provideFactory(
//                repository = appContainer.repository,
//                product = "Americano",
//                redeemableId = null,
//                cartId = null,
//            )
//        )
//        DetailsRoute(
//            detailsViewModel = detailsViewModel,
//            product = "Americano",
//            onBack = {  },
//            onToCart = {  },
//        )
    }
}