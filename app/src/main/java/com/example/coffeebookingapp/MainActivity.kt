package com.example.coffeebookingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.coffeebookingapp.ui.CoffeeBookingApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as CoffeeBookingApplication).container
        setContent {
            CoffeeBookingApp(appContainer)
        }
    }
}