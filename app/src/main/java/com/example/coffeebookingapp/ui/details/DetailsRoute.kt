package com.example.coffeebookingapp.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeebookingapp.model.ProductOption

@Composable
fun DetailsRoute(
    detailsViewModel: DetailsViewModel,
    product: String,
    onBack: () -> Unit,
    onToCart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val option: State<ProductOption> = detailsViewModel.option.collectAsStateWithLifecycle()

    DetailsScreen(
        product = product,
        option = option.value,
        totalPrice = detailsViewModel.price,
        onIncQuantity = { detailsViewModel.incQuantity() },
        onDecQuantity = { detailsViewModel.decQuantity() },
        onSetShot = { detailsViewModel.setShot(it) },
        onSetTemperature = { detailsViewModel.setTemperature(it) },
        onSetSize = { detailsViewModel.setSize(it) },
        onSetIce = { detailsViewModel.setIce(it) },
        onAddToCart = { detailsViewModel.addToCart() },
        onBackClick = onBack,
        onCartClick = onToCart,
        modifier = modifier
    )
}