package com.example.coffeebookingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.model.Order
import com.example.coffeebookingapp.ui.theme.light_onBackground2

@Composable
fun OrderSlot(
    order: Order,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = order.datetime,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = light_onBackground2
                ),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_orders_coffee),
                    contentDescription = null,
                )
                Text(
                    text = order.product,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_orders_location),
                    contentDescription = null,
                )
                Text(
                    text = order.address,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Text(
            text = "$${order.price}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}