package com.example.shop.data

object CartManager {
    private val cartItems = mutableListOf<Product>()

    fun addToCart(product: Product) {
        cartItems.add(product)
    }

    fun getCartItems(): List<Product> = cartItems

    fun removeFromCart(index: Int) {
        cartItems.removeAt(index)
    }
}