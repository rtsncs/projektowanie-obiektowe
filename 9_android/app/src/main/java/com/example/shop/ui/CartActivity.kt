package com.example.shop.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.adapters.ProductAdapter
import com.example.shop.data.CartManager
import com.example.shop.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Cart"

        val cartItems = CartManager.getCartItems()

        binding.rvCart.layoutManager = LinearLayoutManager(this)
        binding.rvCart.adapter = ProductAdapter(cartItems, true)
    }

}