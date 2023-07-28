package com.example.coffeebookingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.model.Redeemable
import com.example.coffeebookingapp.ui.CoffeeAvatar
import com.example.coffeebookingapp.ui.theme.Colors

@Composable
fun RedeemableSlot(
    redeemable: Redeemable,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onButtonClick: () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoffeeAvatar(
                coffee = redeemable.product,
                width = 80.dp,
                height = 80.dp,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = redeemable.product,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "Valid until ${redeemable.validUntil}",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Colors.onBackground2
                    ),
                )
            }
        }
        Button(
            onClick = onButtonClick,
            shape = RoundedCornerShape(50.dp),
            contentPadding = PaddingValues(17.dp, 0.dp),
            modifier = Modifier.align(Alignment.CenterEnd),
            enabled = enabled,
        ) {
            Text(
                text = "${redeemable.pointsRequired} pts",
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}