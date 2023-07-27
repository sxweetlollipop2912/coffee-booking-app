package com.example.coffeebookingapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeebookingapp.ui.navigation.NavRoutes

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    onToCart: () -> Unit,
    onToProfile: () -> Unit,
    onToDetails: (product: String) -> Unit,
    onNavigateToBottomBarRoute: (NavRoutes.MainBottomBar) -> Unit,
    modifier: Modifier = Modifier,
) {
    val fullName by homeViewModel.fullName.collectAsStateWithLifecycle()
    val stampCount by homeViewModel.stampCount.collectAsStateWithLifecycle()
    val products: List<String> = homeViewModel.products

    HomeScreen(
        fullName = fullName,
        stampCount = stampCount,
        coffees = products,
        onCartClick = onToCart,
        onProfileClick = onToProfile,
        onStampCountClick = { homeViewModel.resetStampCount() },
        onCoffeeClick = onToDetails,
        onNavigateToBottomBarRoute = onNavigateToBottomBarRoute,
        modifier = modifier
    )
}