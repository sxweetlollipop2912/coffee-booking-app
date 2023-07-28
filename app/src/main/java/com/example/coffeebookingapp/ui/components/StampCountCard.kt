package com.example.coffeebookingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.ui.theme.Colors
import java.lang.Integer.min
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StampCountCard(
    stampCount: Int,
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    containerVariantColor: Color,
    activeCupTint: Color,
    inactiveCupTint: Color = Colors.inactive,
    onClick: () -> Unit
) {
    val stampCount = min(abs(stampCount), 8)
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp, 15.dp, 20.dp, 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Loyalty card",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = contentColor
                    )
                )
                Text(
                    text = "$stampCount / 8",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = contentColor
                    )
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp,
                ),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = containerVariantColor,
                ),
                onClick = onClick
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // add ic_coffee_cup_active icon the number of stampCount times
                    for (i in 1..stampCount) {
                        Icon(
                            painter = painterResource(R.drawable.cup_glow),
                            tint = activeCupTint,
                            contentDescription = null,
                        )
                    }
                    // add ic_coffee_cup_inactive icon the number of 8 - stampCount times
                    for (i in 1..(8 - stampCount)) {
                        Icon(
                            painter = painterResource(R.drawable.cup_noglow),
                            tint = inactiveCupTint,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}