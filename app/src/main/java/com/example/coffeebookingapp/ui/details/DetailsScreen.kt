package com.example.coffeebookingapp.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.model.IceType
import com.example.coffeebookingapp.model.PointReward
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.ShotType
import com.example.coffeebookingapp.model.SizeType
import com.example.coffeebookingapp.model.TemperatureType
import com.example.coffeebookingapp.ui.CoffeeAvatar
import com.example.coffeebookingapp.ui.components.PointCard
import com.example.coffeebookingapp.ui.components.QuantityButton
import com.example.coffeebookingapp.ui.components.RewardHistorySlot
import com.example.coffeebookingapp.ui.components.StampCountCard
import com.example.coffeebookingapp.ui.theme.buttonTextStyle
import com.example.coffeebookingapp.ui.theme.light_inactive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    product: String,
    viewModel: DetailsViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Details",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO: go back from Details*/ },
                        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back_arrow),
                            contentDescription = "back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO: go to cart from Details*/ },
                        modifier = Modifier.padding(0.dp, 0.dp, 15.dp, 0.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cart),
                            contentDescription = "go to cart",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        }
    ) {
        innerPadding -> val screenModifier = Modifier.padding(innerPadding)
        DetailsScreenContent(product, viewModel, screenModifier)
    }
}

@Composable
fun DetailsScreenContent(
    product: String,
    viewModel: DetailsViewModel,
    modifier: Modifier = Modifier
) {
    val option: State<ProductOption> = viewModel.option.collectAsStateWithLifecycle()
    val totalPrice: State<Int> = viewModel.totalPrice.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .padding(30.dp, 0.dp, 30.dp, 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    CoffeeAvatar(coffee = product, width = 200.dp)
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = product,
                    style = MaterialTheme.typography.labelLarge,
                )
                QuantityButton(
                    quantity = option.value.quantity,
                    onIncrease = { viewModel.incQuantity() },
                    onDecrease = { viewModel.decQuantity() },
                )
            }
            Divider(
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 1.dp,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Shot",
                    style = MaterialTheme.typography.labelLarge,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val current = option.value.shot
                    OutlinedButton(
                        onClick = { viewModel.setShot(ShotType.SINGLE) },
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.2.dp,
                            if (current == ShotType.SINGLE) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        ),
                        contentPadding = PaddingValues(20.dp, 0.dp),
                    ) {
                        Text(
                            text = "Single",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                    OutlinedButton(
                        onClick = { viewModel.setShot(ShotType.DOUBLE) },
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.2.dp,
                            if (current == ShotType.DOUBLE) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        ),
                        contentPadding = PaddingValues(20.dp, 0.dp),
                    ) {
                        Text(
                            text = "Double",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 1.dp,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Select",
                    style = MaterialTheme.typography.labelLarge,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val current = option.value.temperature
                    IconButton(onClick = { viewModel.setTemperature(TemperatureType.HOT) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_details_select_hot),
                            contentDescription = "select hot",
                            tint = if (current == TemperatureType.HOT) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(onClick = { viewModel.setTemperature(TemperatureType.ICED) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_details_select_iced),
                            contentDescription = "select cold",
                            tint = if (current == TemperatureType.ICED) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 1.dp,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Size",
                    style = MaterialTheme.typography.labelLarge,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    val current = option.value.size
                    IconButton(
                        onClick = { viewModel.setSize(SizeType.SMALL) },
                        modifier = Modifier.height(22.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_details_select_small),
                            contentDescription = "select size small",
                            tint = if (current == SizeType.SMALL) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(
                        onClick = { viewModel.setSize(SizeType.MEDIUM) },
                        modifier = Modifier.height(31.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_details_select_medium),
                            contentDescription = "select size medium",
                            tint = if (current == SizeType.MEDIUM) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(
                        onClick = { viewModel.setSize(SizeType.LARGE) },
                        modifier = Modifier.height(38.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_details_select_large),
                            contentDescription = "select size large",
                            tint = if (current == SizeType.LARGE) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 1.dp,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Ice",
                    style = MaterialTheme.typography.labelLarge,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    val current = option.value.ice
                    IconButton(onClick = { viewModel.setIce(IceType.LESS) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_details_select_ice1),
                            contentDescription = "select less ice",
                            tint = if (current == IceType.LESS) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(onClick = { viewModel.setIce(IceType.HALF) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_details_select_ice2),
                            contentDescription = "select half ice",
                            tint = if (current == IceType.HALF) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(onClick = { viewModel.setIce(IceType.FULL) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_details_select_ice3),
                            contentDescription = "select full ice",
                            tint = if (current == IceType.FULL) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                }
            }
        }
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Total Amount",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "$${totalPrice.value}",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Button(
                onClick = { /*TODO: Add to cart*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Add to cart",
                    style = buttonTextStyle
                )
            }
        }
    }
}