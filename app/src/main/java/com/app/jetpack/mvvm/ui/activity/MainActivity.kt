package com.app.jetpack.mvvm.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.jetpack.mvvm.common.ui.theme.JetpackComposeMVVMCleanArchitectureTheme
import com.app.jetpack.mvvm.ui.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
