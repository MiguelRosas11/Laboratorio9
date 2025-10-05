package com.example.laboratorio9.model

data class Product(
    val id: Int,
    val name: String,
    val isWishlisted: Boolean = false
)