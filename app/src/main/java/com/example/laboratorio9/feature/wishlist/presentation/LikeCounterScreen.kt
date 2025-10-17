package com.example.laboratorio9.feature.wishlist.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikeCounterScreen() {
    var likeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Contador de Me Gusta") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Likes: $likeCount", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(24.dp))
            Button(onClick = { likeCount++ }) {
                Text("¡Me Gusta!")
            }
        }
    }
}
