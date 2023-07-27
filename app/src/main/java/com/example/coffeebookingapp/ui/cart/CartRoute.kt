package com.example.coffeebookingapp.ui.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CartRoute(
    cartViewModel: CartViewModel,
    onToDetails: (product: String, cartId: String) -> Unit,
    onToOngoingOrders: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val items by cartViewModel.items.collectAsStateWithLifecycle()
    val uiState by cartViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.checkOutSucceeded) {
        OrderSuccessScreen(onTrackOrderClick = onToOngoingOrders)
    } else {
        CartScreen(
            items = items,
            onNavigateToDetails = { onToDetails(it.product, it.id) },
            onRemoveItem = { cartViewModel.removeFromCart(it) },
            onCheckOut = { cartViewModel.checkOut() },
            onBackClick = onBack,
            modifier = modifier
        )
    }
}