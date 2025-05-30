package com.example.shop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.data.Product
import com.example.shop.data.CartManager

class ProductAdapter(
    private val products: List<Product>,
    private val isCartView: Boolean = false
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val items = products.toMutableList()
    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        private val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val btnAction: Button = itemView.findViewById(R.id.btnAddToCart)

        fun bind(product: Product, position: Int) {
            tvProductName.text = product.name
            tvProductPrice.text = "Price: ${product.price}"
            if (isCartView) {
                btnAction.text = "Remove"
                btnAction.setOnClickListener {
                    CartManager.removeFromCart(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, items.size)
                }
            } else {
                btnAction.setOnClickListener {
                    CartManager.addToCart(product)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], position)
    }

    override fun getItemCount() = products.size

}