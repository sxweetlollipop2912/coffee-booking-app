package com.example.coffeebookingapp.ui.my_orders

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.model.Order
import com.example.coffeebookingapp.ui.UIConfig
import com.example.coffeebookingapp.ui.components.BottomBar
import com.example.coffeebookingapp.ui.components.BottomBarTab
import com.example.coffeebookingapp.ui.components.OrderSlot
import com.example.coffeebookingapp.ui.navigation.NavRoutes
import com.example.coffeebookingapp.ui.theme.light_inactive

data class TabContentData(
    val tab: Tab,
    val content: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOrdersScreen(
    ongoing: List<Order>,
    history: List<Order>,
    currentTab: Tab,
    setTab: (Tab) -> Unit,
    onOngoingClick: (orderId: String) -> Unit,
    onHistoryClick: (orderId: String) -> Unit,
    onNavigateToBottomBarRoute: (NavRoutes.MainBottomBar) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabContentData = listOf(
        TabContentData(Tab.ONGOING) {
            TabContent(ongoing.asReversed(), onOngoingClick, Modifier.padding(bottom = UIConfig.SCREEN_BOTTOM_PADDING))
        },
        TabContentData(Tab.HISTORY) {
            TabContent(history.asReversed(), onHistoryClick, Modifier.padding(bottom = UIConfig.SCREEN_BOTTOM_PADDING))
        }
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "My Orders",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        },
        bottomBar = {
            BottomBar(
                tabs = BottomBarTab.values(),
                currentRoute = NavRoutes.MainBottomBar.ORDERS,
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
        MyOrdersScreenContent(
            tabContentData,
            currentTab,
            setTab,
            screenModifier
        )
    }
}

@Composable
fun MyOrdersScreenContent(
    tabContentData: List<TabContentData>,
    currentTab: Tab,
    onSetTab: (Tab) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentTabIdx = tabContentData.indexOfFirst { it.tab == currentTab }
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = currentTabIdx,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            tabContentData.forEachIndexed { idx, content ->
                val selected = idx == currentTabIdx
                Tab(
                    selected = selected,
                    onClick = { onSetTab(content.tab) },
                ) {
                    Text(
                        text = content.tab.title,
                        color = if (selected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            light_inactive
                        },
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }
        }
        tabContentData[currentTabIdx].content()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TabContent(
    orders: List<Order>,
    onOrderClick: (orderId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 100.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(
            count = orders.size,
            key = { index -> orders[index].id },
        ) { idx ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.StartToEnd)
                || dismissState.isDismissed(DismissDirection.EndToStart)
            ) {
                onOrderClick(orders[idx].id)
            }
            SwipeToDismiss(
                state = dismissState,
                dismissThresholds = {
                    FractionalThreshold(0.5f)
                },
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.Transparent
                            else -> light_inactive
                        },
                        label = ""
                    )
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                    )
                },
            ) {
                Box(
                    modifier = Modifier
                        .clickable { onOrderClick(orders[idx].id) }
                        .padding(horizontal = UIConfig.SCREEN_SIDE_PADDING, vertical = 15.dp)
                ) {
                    OrderSlot(
                        orders[idx]
                    )
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}