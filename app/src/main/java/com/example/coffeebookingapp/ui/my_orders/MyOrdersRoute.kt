package com.example.coffeebookingapp.ui.my_orders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeebookingapp.ui.navigation.NavRoutes

@Composable
fun MyOrdersRoute(
    myOrdersViewModel: MyOrdersViewModel,
    onNavigateToBottomBarRoute: (NavRoutes.MainBottomBar) -> Unit,
    modifier: Modifier = Modifier,
) {
    val ongoing by myOrdersViewModel.ongoing.collectAsStateWithLifecycle()
    val history by myOrdersViewModel.history.collectAsStateWithLifecycle()

    MyOrdersScreen(
        ongoing = ongoing,
        history = history,
        onOngoingClick = { orderId ->
            myOrdersViewModel.moveToHistory(orderId)
        },
        onHistoryClick = { orderId ->
            myOrdersViewModel.moveToOngoing(orderId)
        },
        onNavigateToBottomBarRoute = onNavigateToBottomBarRoute,
        modifier = modifier
    )
}