package com.example.coffeebookingapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.ui.theme.light_onPrimary2

@Composable
fun PointCard(
    points: Int,
    modifier: Modifier = Modifier,
    onRedeemClick: () -> Unit,
) {
    Box {
        Card(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "My Points:",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = light_onPrimary2
                        ),
                    )
                    Text(
                        text = "$points",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = light_onPrimary2,
                            fontWeight = FontWeight.Medium,
                        ),
                    )
                }
                Button(
                    onClick = onRedeemClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(10.dp, 0.dp)
                ) {
                    Text(
                        text = "Redeem drinks",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_rewards_decor),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(10.dp, 20.dp)
        )
    }
}