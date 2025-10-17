package com.example.laboratorio9.feature.wishlist.presentation

import androidx.lifecycle.ViewModel
import com.example.laboratorio9.feature.wishlist.domain.model.Product
import com.example.laboratorio9.feature.wishlist.domain.model.WishlistUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WishlistViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    fun loadProducts() {
        if (_uiState.value.products.isNotEmpty()) return
        val demo = listOf(
            Product(1, "Audífonos Bluetooth"),
            Product(2, "Teclado Mecánico"),
            Product(3, "Monitor 27\""),
            Product(4, "Mouse Inalámbrico"),
            Product(5, "SSD 1TB"),
            Product(6, "Silla Ergonómica"),
            Product(7, "Micrófono USB"),
            Product(8, "Lámpara LED"),
            Product(9, "USB-C Hub"),
            Product(10, "Mochila Laptop")
        )
        _uiState.value = WishlistUiState(demo)
    }

    fun toggleWishlist(productId: Int) {
        val updated = _uiState.value.products.map {
            if (it.id == productId) it.copy(isWishlisted = !it.isWishlisted) else it
        }
        _uiState.value = _uiState.value.copy(products = updated)
    }
}
