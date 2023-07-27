package com.example.coffeebookingapp.ui.my_orders

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeebookingapp.ui.navigation.NavRoutes

@Composable
fun MyOrdersRoute(
    myOrdersViewModel: MyOrdersViewModel,
    onNavigateToBottomBarRoute: (NavRoutes.MainBottomBar) -> Unit,
    modifier: Modifier = Modifier,
) {
    val ongoing = myOrdersViewModel.ongoing.collectAsStateWithLifecycle()
    val history = myOrdersViewModel.history.collectAsStateWithLifecycle()

    MyOrdersScreen(
        ongoing = ongoing.value,
        history = history.value,
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