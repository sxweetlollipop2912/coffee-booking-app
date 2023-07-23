package com.example.coffeebookingapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.ui.theme.light_onPrimary2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StampCountCard(
    stampCount: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
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
                        color = light_onPrimary2
                    )
                )
                Text(
                    text = "$stampCount / 8",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = light_onPrimary2
                    )
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
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
                        Image(
                            painter = painterResource(R.drawable.ic_coffee_cup_active),
                            contentDescription = null,
                        )
                    }
                    // add ic_coffee_cup_inactive icon the number of 8 - stampCount times
                    for (i in 1..(8 - stampCount)) {
                        Image(
                            painter = painterResource(R.drawable.ic_coffee_cup_inactive),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}