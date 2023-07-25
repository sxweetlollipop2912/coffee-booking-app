package com.example.coffeebookingapp.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.model.IceType
import com.example.coffeebookingapp.model.ProductOption
import com.example.coffeebookingapp.model.ShotType
import com.example.coffeebookingapp.model.SizeType
import com.example.coffeebookingapp.model.TemperatureType
import com.example.coffeebookingapp.ui.CoffeeAvatar
import com.example.coffeebookingapp.ui.components.QuantityButton
import com.example.coffeebookingapp.ui.theme.buttonTextStyle
import com.example.coffeebookingapp.ui.theme.light_inactive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    product: String,
    option: ProductOption,
    totalPrice: Double,
    onIncQuantity: () -> Unit,
    onDecQuantity: () -> Unit,
    onSetShot: (ShotType) -> Unit,
    onSetTemperature: (TemperatureType) -> Unit,
    onSetSize: (SizeType) -> Unit,
    onSetIce: (IceType) -> Unit,
    onAddToCart: () -> Unit,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
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
                        onClick = onBackClick,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_arrow),
                            contentDescription = "back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onCartClick,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cart),
                            contentDescription = "go to cart",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                modifier = Modifier.padding(10.dp, 0.dp, 15.dp, 0.dp),
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
        DetailsScreenContent(
            product,
            option,
            totalPrice,
            onIncQuantity,
            onDecQuantity,
            onSetShot,
            onSetTemperature,
            onSetSize,
            onSetIce,
            onAddToCart,
            screenModifier.padding(30.dp, 0.dp, 30.dp, 15.dp)
        )
    }
}

@Composable
fun DetailsScreenContent(
    product: String,
    option: ProductOption,
    totalPrice: Double,
    onIncQuantity: () -> Unit,
    onDecQuantity: () -> Unit,
    onSetShot: (ShotType) -> Unit,
    onSetTemperature: (TemperatureType) -> Unit,
    onSetSize: (SizeType) -> Unit,
    onSetIce: (IceType) -> Unit,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
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
            OptionRow(
                title = product,
                modifier = Modifier.height(48.dp)
            ) {
                QuantityButton(
                    quantity = option.quantity,
                    onIncrease = onIncQuantity,
                    onDecrease = onDecQuantity,
                    width = 85.dp,
                    modifier = Modifier
                        .height(36.dp),
                )
            }
            Divider(
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 1.dp,
            )
            OptionRow(
                title = "Shot",
                modifier = Modifier.height(48.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val current = option.shot
                    OutlinedButton(
                        onClick = { onSetShot(ShotType.SINGLE) },
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(
                            1.2.dp,
                            if (current == ShotType.SINGLE) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        ),
                        contentPadding = PaddingValues(20.dp, 0.dp),
                        modifier = Modifier.height(36.dp),
                    ) {
                        Text(
                            text = "Single",
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                    OutlinedButton(
                        onClick = { onSetShot(ShotType.DOUBLE) },
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(
                            1.2.dp,
                            if (current == ShotType.DOUBLE) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        ),
                        contentPadding = PaddingValues(20.dp, 0.dp),
                        modifier = Modifier.height(36.dp),
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
            OptionRow(
                title = "Select",
                modifier = Modifier.height(48.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val current = option.temperature
                    IconButton(onClick = { onSetTemperature(TemperatureType.HOT) }) {
                        Icon(
                            painter = painterResource(R.drawable.cup_hot),
                            contentDescription = "select hot",
                            tint = if (current == TemperatureType.HOT) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(onClick = { onSetTemperature(TemperatureType.ICED) }) {
                        Icon(
                            painter = painterResource(R.drawable.cup_iced),
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
            OptionRow(
                title = "Size",
                modifier = Modifier.height(48.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    val current = option.size
                    IconButton(
                        onClick = { onSetSize(SizeType.SMALL) },
                        modifier = Modifier.height(22.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cup_small),
                            contentDescription = "select size small",
                            tint = if (current == SizeType.SMALL) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(
                        onClick = { onSetSize(SizeType.MEDIUM) },
                        modifier = Modifier.height(31.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cup_medium),
                            contentDescription = "select size medium",
                            tint = if (current == SizeType.MEDIUM) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(
                        onClick = { onSetSize(SizeType.LARGE) },
                        modifier = Modifier.height(38.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cup_large),
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
            OptionRow(
                title = "Ice",
                modifier = Modifier.height(48.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    val current = option.ice
                    IconButton(onClick = { onSetIce(IceType.LESS) }) {
                        Icon(
                            painter = painterResource(R.drawable.ice1),
                            contentDescription = "select less ice",
                            tint = if (current == IceType.LESS) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(onClick = { onSetIce(IceType.HALF) }) {
                        Icon(
                            painter = painterResource(R.drawable.ice2),
                            contentDescription = "select half ice",
                            tint = if (current == IceType.HALF) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                    IconButton(onClick = { onSetIce(IceType.FULL) }) {
                        Icon(
                            painter = painterResource(R.drawable.ice3),
                            contentDescription = "select full ice",
                            tint = if (current == IceType.FULL) MaterialTheme.colorScheme.onBackground
                            else light_inactive
                        )
                    }
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
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
                    text = "$${totalPrice}",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Button(
                onClick = onAddToCart,
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

@Composable
private fun OptionRow(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
        )
        content()
    }
}