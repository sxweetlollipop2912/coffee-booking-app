package com.example.coffeebookingapp.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailsRoute(
    detailsViewModel: DetailsViewModel,
    product: String,
    onBack: () -> Unit,
    onToCart: () -> Unit,
    onToCartRemoveFromStack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val option by detailsViewModel.option.collectAsStateWithLifecycle()

    DetailsScreen(
        product = product,
        option = option,
        totalPrice = detailsViewModel.price,
        onIncQuantity = { detailsViewModel.incQuantity() },
        onDecQuantity = { detailsViewModel.decQuantity() },
        onSetShot = { detailsViewModel.setShot(it) },
        onSetTemperature = { detailsViewModel.setTemperature(it) },
        onSetSize = { detailsViewModel.setSize(it) },
        onSetIce = { detailsViewModel.setIce(it) },
        onAddToCart = {
            if (detailsViewModel.addToCart()) {
                onToCartRemoveFromStack()
            }
        },
        onBackClick = onBack,
        onCartClick = onToCart,
        modifier = modifier
    )
}