package com.example.salesappkotlinproject.ui.product_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.toSht
import com.example.salesappkotlinproject.model.Product
import kotlinx.android.synthetic.main.item_product_list.view.*

class ProductListAdapter(private val listener: ClickListener): RecyclerView.Adapter<BaseProductListViewHolder>() {

    private var items = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseProductListViewHolder {
        return if (viewType == VIEW_TYPE_DATA) ProductListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_list, parent, false)
        ) else EmptyProductListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_empty_product_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (items.count() == 0) 1
        else items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.count() == 0) VIEW_TYPE_EMPTY
        else VIEW_TYPE_DATA
    }

    override fun onBindViewHolder(holder: BaseProductListViewHolder, position: Int) {
        val type = getItemViewType(position)
        if (type == VIEW_TYPE_DATA) setupItemListViewHolder(holder as ProductListViewHolder, position)
    }

    private fun setupItemListViewHolder(holder: ProductListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    fun addItems(item: MutableList<Product>) {
        items.addAll(item)
        notifyDataSetChanged()
    }

    fun addItem(item: Product) {
        items.add(item)
        notifyDataSetChanged()
//        notifyItemRangeInserted(items.lastIndex, items.count()-1)
    }

    companion object {
        const val VIEW_TYPE_DATA = 1
        const val VIEW_TYPE_EMPTY = 2
    }
}

open class BaseProductListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

class ProductListViewHolder(itemView: View): BaseProductListViewHolder(itemView){
    fun bind(item: Product) {
        itemView.item_product_name.text = item.name
        itemView.item_product_available_number.text = item.available_count.toString().toSht()
    }
}

class EmptyProductListViewHolder(itemView: View): BaseProductListViewHolder(itemView){}

interface ClickListener {
    fun onItemClick(item: Product)
}