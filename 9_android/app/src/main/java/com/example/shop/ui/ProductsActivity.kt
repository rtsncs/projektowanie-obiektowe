package com.example.shop.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.adapters.ProductAdapter
import com.example.shop.data.Product
import com.example.shop.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding

    private val allProducts = listOf(
        Product(1, "Phone", 1200.0, 1),
        Product(2, "Laptop", 2500.0, 1),
        Product(3, "Shirt", 80.0, 2),
        Product(4, "Book", 45.0, 3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryId = intent.getIntExtra("categoryId", -1)
        val categoryName = intent.getStringExtra("categoryName") ?: "Products"

        supportActionBar?.title = categoryName

        val products = allProducts.filter { it.categoryId == categoryId }
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = ProductAdapter(products)
    }

}