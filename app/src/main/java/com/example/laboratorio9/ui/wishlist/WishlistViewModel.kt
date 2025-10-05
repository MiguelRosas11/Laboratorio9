package com.example.laboratorio9.ui.wishlist

import androidx.lifecycle.ViewModel
import com.example.laboratorio9.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WishlistViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        val products = listOf(
            Product(1, "Smartphone Samsung Galaxy S24"),
            Product(2, "Laptop Dell XPS 15"),
            Product(3, "Audífonos Sony WH-1000XM5"),
            Product(4, "Tablet iPad Air"),
            Product(5, "Smartwatch Apple Watch Series 9"),
            Product(6, "Cámara Canon EOS R6"),
            Product(7, "Consola PlayStation 5"),
            Product(8, "Teclado Mecánico Logitech"),
            Product(9, "Monitor LG UltraWide 34\""),
            Product(10, "Mouse Logitech MX Master 3S")
        )

        _uiState.update { currentState ->
            currentState.copy(products = products)
        }
    }

    fun toggleWishlist(productId: Int) {
        _uiState.update { currentState ->
            val updatedProducts = currentState.products.map { product ->
                if (product.id == productId) {
                    product.copy(isWishlisted = !product.isWishlisted)
                } else {
                    product
                }
            }
            currentState.copy(products = updatedProducts)
        }
    }
}