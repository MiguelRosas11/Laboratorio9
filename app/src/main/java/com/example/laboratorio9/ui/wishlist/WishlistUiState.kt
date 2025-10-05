package com.example.laboratorio9.ui.wishlist
import com.example.laboratorio9.model.Product

data class WishlistUiState(
    val products: List<Product> = emptyList()
)