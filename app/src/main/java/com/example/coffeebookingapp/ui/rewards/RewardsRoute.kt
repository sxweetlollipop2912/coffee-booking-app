package com.example.coffeebookingapp.ui.rewards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.ui.navigation.NavRoutes

@Composable
fun RewardsRoute(
    rewardsViewModel: RewardsViewModel,
    onToRedeem: () -> Unit,
    onNavigateToBottomBarRoute: (NavRoutes.Main) -> Unit,
    modifier: Modifier = Modifier,
) {
    val stampCount: State<Int> = rewardsViewModel.stampCount.collectAsStateWithLifecycle()
    val points: State<Int> = rewardsViewModel.points.collectAsStateWithLifecycle()
    val history: State<List<PointReward>> = rewardsViewModel.history.collectAsStateWithLifecycle()

    RewardsScreen(
        stampCount = stampCount.value,
        points = points.value,
        history = history.value,
        onStampCardClick = { rewardsViewModel.resetStampCount() },
        onRedeemDrinksClick = onToRedeem,
        onNavigateToBottomBarRoute = onNavigateToBottomBarRoute,
        modifier = modifier
    )
}