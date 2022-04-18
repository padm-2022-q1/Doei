package com.example.doei.ui.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doei.R
import com.example.doei.databinding.ProductAdapterBinding
import com.example.doei.models.Product

class ProductAdapter(private val producList: List<Product>, private val activity: Activity, private val listener: Listener): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductAdapterBinding.inflate(LayoutInflater.from(activity.applicationContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = producList[position]
        holder.tbProductName.text = product.name
        holder.tvDistance.text = product.distance
        val itemImage = when(position){
            0 -> R.drawable.geladeiraimg
            1 -> R.drawable.sofa_antigo
            else -> R.drawable.sofa_antigo
        }
        Glide.with(activity)
            .load(itemImage)
            .into(holder.ivProduct)

        holder.itemView.setOnClickListener {
            listener.onProductClick(product)
        }
    }

    override fun getItemCount(): Int {
        return producList.size
    }

    class ViewHolder(view: ProductAdapterBinding): RecyclerView.ViewHolder(view.root) {
        val ivProduct = view.ivProduct
        val tbProductName = view.tvProductName
        val tvDistance = view.tvDistance
    }

    interface Listener {
        fun onProductClick(product: Product)
    }

}