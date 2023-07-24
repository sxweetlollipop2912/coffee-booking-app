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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.model.Order
import com.example.coffeebookingapp.ui.components.BottomBar
import com.example.coffeebookingapp.ui.components.BottomBarTab
import com.example.coffeebookingapp.ui.components.OrderSlot
import com.example.coffeebookingapp.ui.navigation.NavRoutes
import com.example.coffeebookingapp.ui.theme.light_inactive

enum class Section(val title: String) {
    ONGOING("Ongoing"),
    HISTORY("History"),
}

data class TabContent(
    val section: Section,
    val content: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOrdersScreen(
    ongoing: List<Order>,
    history: List<Order>,
    onOngoingClick: (orderId: String) -> Unit,
    onHistoryClick: (orderId: String) -> Unit,
    onNavigateToBottomBarRoute: (NavRoutes.Main) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabContents = getTabContents(
        firstContent = ongoing,
        secondContent = history,
        onFirstContentClick = onOngoingClick,
        onSecondContentClick = onHistoryClick,
        modifier = Modifier.padding(bottom = 20.dp)
    )
    val (currentSection, setSection) = rememberSaveable {
        mutableStateOf(tabContents.first().section)
    }

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
                currentRoute = NavRoutes.Main.ORDERS,
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
            tabContents,
            currentSection,
            setSection,
            screenModifier
        )
    }
}

@Composable
fun MyOrdersScreenContent(
    tabContents: List<TabContent>,
    currentSection: Section,
    onSetSection: (Section) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTabIndex = tabContents.indexOfFirst { it.section == currentSection }
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            tabContents.forEachIndexed { idx, content ->
                Tab(
                    selected = selectedTabIndex == idx,
                    onClick = { onSetSection(content.section) },
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = content.section.title,
                        color = if (selectedTabIndex == idx) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                light_inactive
                            },
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
        Box {
            tabContents[selectedTabIndex].content()
        }
    }
}

fun getTabContents(
    firstContent: List<Order>,
    secondContent: List<Order>,
    onFirstContentClick: (orderId: String) -> Unit,
    onSecondContentClick: (orderId: String) -> Unit,
    modifier: Modifier = Modifier
): List<TabContent> {
    val firstSection = TabContent(Section.ONGOING) {
        SectionContent(firstContent, onFirstContentClick, modifier)
    }
    val secondSection = TabContent(Section.HISTORY) {
        SectionContent(secondContent, onSecondContentClick, modifier)
    }
    return listOf(firstSection, secondSection)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SectionContent(
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
                || dismissState.isDismissed(DismissDirection.EndToStart)) {
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
                    Box(Modifier
                        .fillMaxSize()
                        .background(color)
                    )
                },
            ) {
                Box(
                    modifier = Modifier
                        .clickable { onOrderClick(orders[idx].id) }
                        .padding(horizontal = 30.dp, vertical = 15.dp)
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