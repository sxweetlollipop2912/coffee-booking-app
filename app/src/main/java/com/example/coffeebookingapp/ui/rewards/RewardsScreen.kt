package com.example.coffeebookingapp.ui.rewards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.ui.UIConfig
import com.example.coffeebookingapp.ui.components.BottomBar
import com.example.coffeebookingapp.ui.components.BottomBarTab
import com.example.coffeebookingapp.ui.components.PointCard
import com.example.coffeebookingapp.ui.components.RewardHistorySlot
import com.example.coffeebookingapp.ui.components.StampCountCard
import com.example.coffeebookingapp.ui.navigation.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen(
    stampCount: Int,
    points: Int,
    history: List<PointReward>,
    onStampCardClick: () -> Unit,
    onRedeemDrinksClick: () -> Unit,
    onNavigateToBottomBarRoute: (NavRoutes.MainBottomBar) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Rewards",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomBar(
                tabs = BottomBarTab.values(),
                currentRoute = NavRoutes.MainBottomBar.REWARDS_HISTORY,
                navigateToBottomBarRoute = onNavigateToBottomBarRoute
            )
        },
    ) { innerPadding ->
        val screenModifier = Modifier.padding(
            innerPadding.calculateStartPadding(LayoutDirection.Ltr),
            innerPadding.calculateTopPadding(),
            innerPadding.calculateEndPadding(LayoutDirection.Ltr),
            0.dp,
        )
        RewardsScreenContent(
            stampCount,
            points,
            history,
            onStampCardClick,
            onRedeemDrinksClick,
            screenModifier.padding(
                start = UIConfig.SCREEN_SIDE_PADDING,
                end = UIConfig.SCREEN_SIDE_PADDING,
                bottom = UIConfig.SCREEN_BOTTOM_PADDING
            )
        )
    }
}

@Composable
fun RewardsScreenContent(
    stampCount: Int,
    points: Int,
    history: List<PointReward>,
    onStampCardClick: () -> Unit,
    onRedeemDrinksClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                StampCountCard(
                    stampCount = stampCount,
                    onClick = onStampCardClick
                )
                PointCard(
                    points = points,
                    onRedeemClick = onRedeemDrinksClick
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "History Rewards",
                style = MaterialTheme.typography.labelLarge,
            )
        }
        items(
            count = history.size,
            key = { index -> history[index].id },
        ) { index ->
            RewardHistorySlot(reward = history[history.lastIndex - index])
            if (index < history.lastIndex) {
                Spacer(modifier = Modifier.height(15.dp))
                Divider(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    thickness = 1.dp
                )
            }
        }
    }
}