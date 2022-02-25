package com.example.currency.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.data.model.CurrencyInfo
import com.example.currency.databinding.ItemCurrencyBinding
import com.example.currency.utils.RecyclerViewCallback

class CurrencyAdapter(private var currencyList: List<CurrencyInfo>) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private var itemCallback: RecyclerViewCallback<CurrencyInfo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currencyList[position]
        holder.tvIcon.text = item.name.toCharArray()[0]?.toString()
        holder.tvName.text = item.name
        holder.tvSymbol.text = item.symbol

        holder.itemView.setOnClickListener {
            itemCallback?.onItemSelected(position, item)
        }
    }

    override fun getItemCount(): Int = currencyList.size

    inner class ViewHolder(binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvIcon: TextView = binding.tvIcon
        val tvName: TextView = binding.tvName
        val tvSymbol: TextView = binding.tvSymbol
    }

    fun update(currencyList: List<CurrencyInfo>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    fun setCallback(itemSelected: RecyclerViewCallback<CurrencyInfo>) {
        this.itemCallback = itemSelected
    }

}