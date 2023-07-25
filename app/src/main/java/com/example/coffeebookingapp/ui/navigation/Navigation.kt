package com.example.coffeebookingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavRoutes {
    enum class Main(val route: String) {
        HOME("home"),
        CART("cart"),
        PROFILE("profile"),
        REWARDS_HISTORY("rewards_history"),
        REDEEM("redeem"),
        ORDERS("orders"),
    }

    enum class Sub(val route: String, val arg: List<String>) {
        DETAILS(
            "details",
            listOf("productId", "redeemableId", "cartId")
        ),
    }
}

@Composable
fun rememberCoffeeNavController(
    navController: NavHostController = rememberNavController()
): CoffeeNavController = remember(navController) {
    CoffeeNavController(navController)
}

@Stable
class CoffeeNavController(
    val navController: NavHostController,
) {
    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToMain(destination: NavRoutes.Main) {
        val route = destination.route
        if (route != currentRoute) {
            navController.navigate(route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // re-selecting the same item
                launchSingleTop = true
                // Restore state when re-selecting a previously selected item
                restoreState = true
            }
        }
    }

    fun navigateToDetails(
        from: NavBackStackEntry,
        product: String,
        redeemableId: String? = null,
        cartId: String? = null
    ) {
        val base = "${NavRoutes.Sub.DETAILS.route}/$product"
        var optional = ""
        if (redeemableId != null) {
            optional = "?${NavRoutes.Sub.DETAILS.arg[1]}=$redeemableId"
            if (cartId != null) {
                optional += "&${NavRoutes.Sub.DETAILS.arg[2]}=$cartId"
            }
        } else if (cartId != null) {
            optional = "?${NavRoutes.Sub.DETAILS.arg[2]}=$cartId"
        }
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("$base$optional")
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED