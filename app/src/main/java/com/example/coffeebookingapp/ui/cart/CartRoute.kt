package com.example.coffeebookingapp.ui.cart

import androidx.compose.runtime.Composable
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
    val items = cartViewModel.items.collectAsStateWithLifecycle()
    val uiState = cartViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.value.checkOutSucceeded) {
        OrderSuccessScreen(onTrackOrderClick = onToOngoingOrders)
    } else {
        CartScreen(
            items = items.value,
            onNavigateToDetails = { onToDetails(it.product, it.id) },
            onRemoveItem = { cartViewModel.removeFromCart(it) },
            onCheckOut = { cartViewModel.checkOut() },
            onBackClick = onBack,
            modifier = modifier
        )
    }
}