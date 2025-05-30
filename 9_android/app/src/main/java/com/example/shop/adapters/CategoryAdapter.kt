package com.example.shop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.shop.data.Category
import com.example.shop.R

class CategoryAdapter(
    private val categories: List<Category>,
    private val onClick: (Category) -> Unit
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
        fun bind(category: Category) {
            tvCategoryName.text = category.name
            itemView.setOnClickListener{ onClick(category) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size
}