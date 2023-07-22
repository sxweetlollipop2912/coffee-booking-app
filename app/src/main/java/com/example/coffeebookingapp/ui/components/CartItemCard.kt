package com.example.coffeebookingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.model.CartItem
import com.example.coffeebookingapp.ui.CoffeeAvatar
import com.example.coffeebookingapp.ui.theme.light_onSurface2

@Composable
fun CartItemCard(
    item: CartItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp, 15.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoffeeAvatar(
                    coffee = item.product,
                    width = 80.dp
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column {
                    Text(
                        text = item.product,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "${item.option.shot} | ${item.option.temperature} | ${item.option.size} | ${item.option.ice}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = light_onSurface2,
                            fontWeight = FontWeight.Light,
                        ),
                    )
                    Text(
                        text = "x ${item.option.quantity}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = light_onSurface2,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
            }
            Text(
                text = "$${item.price}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}