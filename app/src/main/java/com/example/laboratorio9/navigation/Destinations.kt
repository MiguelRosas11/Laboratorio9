package com.example.laboratorio9.navigation

sealed class Destinations(val route: String) {
    data object Root : Destinations("root")
    data object Wishlist : Destinations("wishlist")
    data object Profile : Destinations("profile")
}
