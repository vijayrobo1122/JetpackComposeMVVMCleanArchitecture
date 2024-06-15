package com.app.jetpack.mvvm.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.jetpack.mvvm.common.ui.theme.JetpackComposeMVVMCleanArchitectureTheme
import com.app.jetpack.mvvm.ui.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeMVVMCleanArchitectureTheme {
                MainScreen()
            }
        }
    }
}


