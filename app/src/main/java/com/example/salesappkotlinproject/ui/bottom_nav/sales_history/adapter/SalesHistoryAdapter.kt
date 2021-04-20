package com.example.salesappkotlinproject.ui.bottom_nav.sales_history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappkotlinproject.R
import com.example.salesappkotlinproject.helper.toSht
import com.example.salesappkotlinproject.helper.toSom
import com.example.salesappkotlinproject.model.Product
import kotlinx.android.synthetic.main.item_empty_product_list.view.*
import kotlinx.android.synthetic.main.item_sales_history.view.*

class SalesHistoryAdapter: RecyclerView.Adapter<BaseSalesViewHolder>() {

    private var items = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSalesViewHolder {
        return if (viewType == VIEW_TYPE_DATA) SalesHistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sales_history, parent, false)
        ) else EmptySalesViewHolder(
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

    override fun onBindViewHolder(holder: BaseSalesViewHolder, position: Int) {
        val type = getItemViewType(position)
        if (type == VIEW_TYPE_DATA) setupSalesHistoryViewHolder(holder as SalesHistoryViewHolder, position)
        else setupEmptySalesViewHolder (holder as EmptySalesViewHolder)
    }

    private fun setupSalesHistoryViewHolder(holder: SalesHistoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    private fun setupEmptySalesViewHolder(holder: EmptySalesViewHolder) {
        holder.bind()
    }

    fun addItems(item: MutableList<Product>) {
        items.addAll(item)
        notifyDataSetChanged()
    }

    companion object {
        const val VIEW_TYPE_DATA = 1
        const val VIEW_TYPE_EMPTY = 2
    }
}

open class BaseSalesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

class SalesHistoryViewHolder(itemView: View): BaseSalesViewHolder(itemView){
    fun bind(item: Product) {
        itemView.tv_item_sales_name.text = item.name
        itemView.tv_item_sales_sold_number.text = item.count_sold.toString().toSht()
        itemView.tv_item_sales_price.text = item.sale_price.toString().toSom()
    }
}

class EmptySalesViewHolder(itemView: View): BaseSalesViewHolder(itemView){
    fun bind(){
        itemView.tv_empty.text = "НЕТ ПРОДАЖ"
    }
}