package com.example.coffeebookingapp.ui.rewards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.ui.components.PointCard
import com.example.coffeebookingapp.ui.components.RewardHistorySlot
import com.example.coffeebookingapp.ui.components.StampCountCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen(
    stampCount: Int,
    points: Int,
    rewards: List<PointReward>,
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
        }
    ) {
        innerPadding -> val screenModifier = Modifier.padding(innerPadding)
        RewardsScreenContent(
            stampCount,
            points,
            rewards,
            screenModifier
        )
    }
}

@Composable
fun RewardsScreenContent(
    stampCount: Int,
    points: Int,
    rewards: List<PointReward>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        content = {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                ) {
                    StampCountCard(stampCount = stampCount)
                    PointCard(points = points)
                }
            }
            item {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "History Rewards",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
            items(rewards.size) { index ->
                RewardHistorySlot(reward = rewards[index])
                if (index < rewards.lastIndex) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        thickness = 1.dp
                    )
                }
            }
        },
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(30.dp, 0.dp, 30.dp, 100.dp)
    )
}