// MainActivity.kt
package com.example.laboratorio9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.laboratorio9.ui.theme.Laboratorio9Theme
import com.example.laboratorio9.ui.wishlist.WishlistScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio9Theme {
                WishlistScreen()
            }
        }
    }
}