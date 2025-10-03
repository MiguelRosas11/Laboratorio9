// MainActivity.kt
package com.example.laboratorio9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.laboratorio9.ui.theme.LikeCounterScreen
import com.example.laboratorio9.ui.theme.Laboratorio9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio9Theme {
                LikeCounterScreen()
            }
        }
    }
}