package com.example.coffeebookingapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.ui.navigation.NavRoutes
import com.example.coffeebookingapp.ui.theme.light_inactive

enum class BottomBarTab(
    val title: String,
    @DrawableRes val iconId: Int,
    val route: NavRoutes.Main
) {
    HOME("Home", R.drawable.ic_nav_home_active, NavRoutes.Main.HOME),
    REWARDS("Rewards", R.drawable.ic_nav_rewards_active, NavRoutes.Main.REWARDS_HISTORY),
    ORDERS("My Orders", R.drawable.ic_nav_orders_active, NavRoutes.Main.ORDERS),
}

@Composable
fun BottomBar(
    tabs: Array<BottomBarTab>,
    currentRoute: NavRoutes.Main,
    navigateToBottomBarRoute: (route: NavRoutes.Main) -> Unit,
) {
    val currentTab = tabs.first { it.route == currentRoute }
    BottomBarLayout {
        tabs.forEach { tab ->
            val selected = (tab == currentTab)
            IconButton(onClick = { navigateToBottomBarRoute(tab.route) }) {
                Icon(
                    painter = painterResource(tab.iconId),
                    tint = if (selected) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        light_inactive
                    },
                    contentDescription = tab.title
                )
            }
        }
    }
}

@Composable
private fun BottomBarLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 24.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                content()
            }
        }
    }
}