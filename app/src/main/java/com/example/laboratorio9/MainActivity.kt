package com.example.laboratorio9.ui
import com.example.laboratorio9.feature.wishlist.presentation.LikeCounterScreen
//Para el screen de LikeCounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.laboratorio9.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavGraph()
        }
    }
}
