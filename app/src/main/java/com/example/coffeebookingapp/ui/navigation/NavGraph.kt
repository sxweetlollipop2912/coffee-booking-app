package com.example.coffeebookingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coffeebookingapp.data.AppContainer
import com.example.coffeebookingapp.ui.cart.CartRoute
import com.example.coffeebookingapp.ui.cart.CartViewModel
import com.example.coffeebookingapp.ui.details.DetailsRoute
import com.example.coffeebookingapp.ui.details.DetailsViewModel
import com.example.coffeebookingapp.ui.home.HomeRoute
import com.example.coffeebookingapp.ui.home.HomeViewModel
import com.example.coffeebookingapp.ui.my_orders.MyOrdersRoute
import com.example.coffeebookingapp.ui.my_orders.MyOrdersViewModel
import com.example.coffeebookingapp.ui.profile.ProfileRoute
import com.example.coffeebookingapp.ui.profile.ProfileViewModel
import com.example.coffeebookingapp.ui.redeem.RedeemRoute
import com.example.coffeebookingapp.ui.redeem.RedeemViewModel
import com.example.coffeebookingapp.ui.rewards.RewardsRoute
import com.example.coffeebookingapp.ui.rewards.RewardsViewModel

@Composable
fun CoffeeNavGraph(
    modifier: Modifier = Modifier,
    appContainer: AppContainer,
    coffeeNavController: CoffeeNavController,
    startDestination: String,
) {
    NavHost(
        navController = coffeeNavController.navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavRoutes.MainBottomBar.HOME.route) { from ->
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    repository = appContainer.repository,
                )
            )
            HomeRoute(
                homeViewModel = homeViewModel,
                onToCart = { coffeeNavController.navigateToMainOther(NavRoutes.MainOther.CART) },
                onToProfile = { coffeeNavController.navigateToMainOther(NavRoutes.MainOther.PROFILE) },
                onToDetails = { product ->
                    coffeeNavController.navigateToDetails(
                        from = from,
                        product = product
                    )
                },
                onNavigateToBottomBarRoute = { coffeeNavController.navigateToBottomBar(it) },
            )
        }
        composable(
            route =
            NavRoutes.Sub.DETAILS.route +
                    "/{${NavRoutes.Sub.DETAILS.args[0]}}" +
                    "?${NavRoutes.Sub.DETAILS.args[1]}={${NavRoutes.Sub.DETAILS.args[1]}}" +
                    "&${NavRoutes.Sub.DETAILS.args[2]}={${NavRoutes.Sub.DETAILS.args[2]}}",
            arguments = listOf(
                navArgument(NavRoutes.Sub.DETAILS.args[0]) {
                    type = NavType.StringType
                },
                navArgument(NavRoutes.Sub.DETAILS.args[1]) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(NavRoutes.Sub.DETAILS.args[2]) {
                    type = NavType.StringType
                    nullable = true
                },
            )
        ) { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val product = requireNotNull(arguments.getString(NavRoutes.Sub.DETAILS.args[0]))
            val redeemableId = arguments.getString(NavRoutes.Sub.DETAILS.args[1])
            val cartId = arguments.getString(NavRoutes.Sub.DETAILS.args[2])

            val detailsViewModel: DetailsViewModel = viewModel(
                factory = DetailsViewModel.provideFactory(
                    repository = appContainer.repository,
                    product = product,
                    redeemableId = redeemableId,
                    cartId = cartId,
                )
            )
            DetailsRoute(
                detailsViewModel = detailsViewModel,
                product = product,
                onBack = { coffeeNavController.navigateUp() },
                onToCart = { coffeeNavController.navigateToMainOther(NavRoutes.MainOther.CART) },
            )
        }
        composable(NavRoutes.MainOther.CART.route) { from ->
            val cartViewModel: CartViewModel = viewModel(
                factory = CartViewModel.provideFactory(
                    repository = appContainer.repository,
                )
            )
            CartRoute(
                cartViewModel = cartViewModel,
                onToDetails = { product, cartId ->
                    coffeeNavController.navigateToDetails(
                        from = from,
                        product = product,
                        cartId = cartId
                    )
                },
                onToOngoingOrders = { coffeeNavController.navigateToBottomBar(NavRoutes.MainBottomBar.ORDERS) },
                onBack = { coffeeNavController.navigateUp() }
            )
        }
        composable(NavRoutes.MainOther.PROFILE.route) {
            val profileViewModel: ProfileViewModel = viewModel(
                factory = ProfileViewModel.provideFactory(
                    repository = appContainer.repository,
                )
            )
            ProfileRoute(
                profileViewModel = profileViewModel,
                onBack = { coffeeNavController.navigateUp() },
            )
        }
        composable(NavRoutes.MainBottomBar.REWARDS_HISTORY.route) {
            val rewardsViewModel: RewardsViewModel = viewModel(
                factory = RewardsViewModel.provideFactory(
                    repository = appContainer.repository,
                )
            )
            RewardsRoute(
                rewardsViewModel = rewardsViewModel,
                onToRedeem = { coffeeNavController.navigateToMainOther(NavRoutes.MainOther.REDEEM) },
                onNavigateToBottomBarRoute = { coffeeNavController.navigateToBottomBar(it) },
            )
        }
        composable(NavRoutes.MainOther.REDEEM.route) { from ->
            val redeemViewModel: RedeemViewModel = viewModel(
                factory = RedeemViewModel.provideFactory(
                    repository = appContainer.repository,
                )
            )
            RedeemRoute(
                redeemViewModel = redeemViewModel,
                onToDetails = { product, redeemableId ->
                    coffeeNavController.navigateToDetails(
                        from = from,
                        product = product,
                        redeemableId = redeemableId
                    )
                },
                onBack = { coffeeNavController.navigateUp() },
            )
        }
        composable(NavRoutes.MainBottomBar.ORDERS.route) {
            val myOrdersViewModel: MyOrdersViewModel = viewModel(
                factory = MyOrdersViewModel.provideFactory(
                    repository = appContainer.repository,
                )
            )
            MyOrdersRoute(
                myOrdersViewModel = myOrdersViewModel,
                onNavigateToBottomBarRoute = { coffeeNavController.navigateToBottomBar(it) },
            )
        }
    }
}
