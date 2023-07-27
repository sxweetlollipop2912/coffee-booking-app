package com.example.coffeebookingapp.ui.rewards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeebookingapp.ui.navigation.NavRoutes

@Composable
fun RewardsRoute(
    rewardsViewModel: RewardsViewModel,
    onToRedeem: () -> Unit,
    onNavigateToBottomBarRoute: (NavRoutes.MainBottomBar) -> Unit,
    modifier: Modifier = Modifier,
) {
    val stampCount by rewardsViewModel.stampCount.collectAsStateWithLifecycle()
    val points by rewardsViewModel.points.collectAsStateWithLifecycle()
    val history by rewardsViewModel.history.collectAsStateWithLifecycle()

    RewardsScreen(
        stampCount = stampCount,
        points = points,
        history = history,
        onStampCardClick = { rewardsViewModel.resetStampCount() },
        onRedeemDrinksClick = onToRedeem,
        onNavigateToBottomBarRoute = onNavigateToBottomBarRoute,
        modifier = modifier
    )
}