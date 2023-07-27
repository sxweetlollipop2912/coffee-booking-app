package com.example.coffeebookingapp.ui.redeem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RedeemRoute(
    redeemViewModel: RedeemViewModel,
    onToDetails: (product: String, redeemableId: String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val redeemable by redeemViewModel.redeemable.collectAsStateWithLifecycle()
    val currentPoints by redeemViewModel.points.collectAsStateWithLifecycle()

    RedeemScreen(
        redeemable = redeemable,
        enabled = redeemable.map { currentPoints >= it.pointsRequired },
        onRedeemableClick = {
            if (redeemViewModel.checkIfRedeemable(it.id))
                onToDetails(it.product, it.id)
        },
        onBackClick = onBack,
        modifier = modifier
    )
}