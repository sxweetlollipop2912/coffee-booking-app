package com.example.coffeebookingapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeebookingapp.ui.navigation.NavRoutes

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    onToCart: () -> Unit,
    onToProfile: () -> Unit,
    onToDetails: (product: String) -> Unit,
    onNavigateToBottomBarRoute: (NavRoutes.Main) -> Unit,
    modifier: Modifier = Modifier,
) {
    val fullName: State<String> = homeViewModel.fullName.collectAsStateWithLifecycle()
    val stampCount: State<Int> = homeViewModel.stampCount.collectAsStateWithLifecycle()
    val products: List<String> = homeViewModel.products

    HomeScreen(
        fullName = fullName.value,
        stampCount = stampCount.value,
        coffees = products,
        onCartClick = onToCart,
        onProfileClick = onToProfile,
        onStampCountClick = { homeViewModel.resetStampCount() },
        onCoffeeClick = onToDetails,
        onNavigateToBottomBarRoute = onNavigateToBottomBarRoute,
        modifier = modifier
    )
}