package com.example.coffeebookingapp.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.model.CartItem
import com.example.coffeebookingapp.ui.components.CartItemCard
import com.example.coffeebookingapp.ui.theme.buttonTextStyle
import com.example.coffeebookingapp.ui.theme.light_darkPrimary
import com.example.coffeebookingapp.ui.theme.light_onBackground2
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    items: List<CartItem>,
    onNavigateToDetails: (CartItem) -> Unit,
    onRemoveItem: (String) -> Boolean,
    onCheckOut: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "My Cart",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back_arrow),
                            contentDescription = "back",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
        CartScreenContent(
            items, onNavigateToDetails, onRemoveItem, onCheckOut,
            screenModifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun CartScreenContent(
    items: List<CartItem>,
    onNavigateToDetails: (CartItem) -> Unit,
    onRemoveItem: (String) -> Boolean,
    onCheckOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val totalPrice = items.sumOf { it.price }
    Column(
        modifier = modifier
            .padding(bottom = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f),
        ) {
            items(
                count = items.size,
                key = { index -> items[index].id },
            ) { index ->
                val dismissState = rememberDismissState()
                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    if (!onRemoveItem(items[index].id)) {
                        LaunchedEffect(Unit) {
                            dismissState.reset()
                        }
                    }
                }
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {
                        FractionalThreshold(0.5f)
                    },
                    background = {
                        Box(
                            Modifier.fillMaxSize(),
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(with(LocalDensity.current) {
                                        maxOf(
                                            abs(dismissState.offset.value).toDp() - 10.dp,
                                            0.dp
                                        )
                                    })
                                    .align(Alignment.CenterEnd),
                                shape = RoundedCornerShape(15.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.errorContainer
                                )
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_delete),
                                        contentDescription = "delete item",
                                        tint = MaterialTheme.colorScheme.onErrorContainer,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    },
                    dismissContent = {
                        CartItemCard(
                            item = items[index],
                            onClick = { onNavigateToDetails(items[index]) },
                        )
                    }
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Total Price",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = light_onBackground2
                    ),
                )
                Text(
                    text = "$${totalPrice}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = light_darkPrimary
                    ),
                )
            }
            Button(
                onClick = onCheckOut,
                contentPadding = PaddingValues(30.dp, 12.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cart),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Checkout",
                    style = buttonTextStyle
                )
            }
        }
    }
}
