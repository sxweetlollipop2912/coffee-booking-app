package com.example.coffeebookingapp.ui.order_success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.ui.theme.buttonTextStyle
import com.example.coffeebookingapp.ui.theme.light_darkPrimary
import com.example.coffeebookingapp.ui.theme.light_onBackground2

@Composable
fun OrderSuccessScreen() {
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_takeaway),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Order Success",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium,
                color = light_darkPrimary
            ),
        )
        Text(
            text = "Your order has been placed successfully.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = light_onBackground2
            ),
        )
        Text(
            text = "For more details, go to my orders.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = light_onBackground2
            ),
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { /*TODO: Track my order navigation*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Track My Order",
                style = buttonTextStyle
            )
        }
    }
}