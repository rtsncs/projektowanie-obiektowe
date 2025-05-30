package com.example.shop.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.adapters.CategoryAdapter
import com.example.shop.data.Category
import com.example.shop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val categories = listOf(
        Category(1, "Category 1"),
        Category(2, "Category 2"),
        Category(3, "Category 3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCategories.layoutManager = LinearLayoutManager(this)

        binding.rvCategories.adapter = CategoryAdapter(categories) { category ->
            val intent = Intent(this, ProductsActivity::class.java)
            intent.putExtra("categoryId", category.id)
            intent.putExtra("categoryName", category.name)
            startActivity(intent)
        }

        binding.btnGoToCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}