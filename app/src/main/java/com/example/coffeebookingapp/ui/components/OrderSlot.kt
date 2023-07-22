package com.example.coffeebookingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
    ) {
        Column {
            Text(
                text = order.datetime,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = light_onBackground2
                ),
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_orders_coffee),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = order.product,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_orders_location),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(10.dp))
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