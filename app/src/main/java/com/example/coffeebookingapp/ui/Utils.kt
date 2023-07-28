package com.example.coffeebookingapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.coffeebookingapp.R

@Composable
fun CoffeeAvatar(
    coffee: String,
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier
) {
    val coffee = coffee.lowercase()
    val painter = when (coffee) {
        "americano" -> painterResource(id = R.drawable.americano)
        "cappuccino" -> painterResource(id = R.drawable.cappuccino)
        "latte" -> painterResource(id = R.drawable.latte)
        else -> painterResource(id = R.drawable.flatwhite)
    }
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier.width(width).height(height),
        contentScale = ContentScale.Inside
    )
}
