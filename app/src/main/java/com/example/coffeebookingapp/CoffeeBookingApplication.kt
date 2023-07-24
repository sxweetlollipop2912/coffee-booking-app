package com.example.coffeebookingapp

import android.app.Application
import com.example.coffeebookingapp.data.AppContainer
import com.example.coffeebookingapp.data.AppContainerImpl

class CoffeeBookingApplication : Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}