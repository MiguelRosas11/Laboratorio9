package com.example.laboratorio9.feature.wishlist.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.laboratorio9.feature.wishlist.domain.model.Product
import com.example.laboratorio9.feature.wishlist.domain.model.WishlistUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    state: WishlistUiState,
    onToggle: (Int) -> Unit,
    onGoToProfile: () -> Unit,
    onLoadIfNeeded: () -> Unit
) {
    LaunchedEffect(Unit) {
        if (state.products.isEmpty()) onLoadIfNeeded()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Wishlist") },
                actions = { TextButton(onClick = onGoToProfile) { Text("Perfil") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.products, key = { it.id }) { p ->
                ProductRow(p, onToggle)
            }
        }
    }
}

@Composable
private fun ProductRow(p: Product, onToggle: (Int) -> Unit) {
    Card {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable { onToggle(p.id) }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(p.name, style = MaterialTheme.typography.titleMedium)
            Text(if (p.isWishlisted) "★" else "☆", style = MaterialTheme.typography.titleLarge)
        }
    }
}
