package com.example.currency.utils

interface RecyclerViewCallback<T> {
    fun onItemSelected(position: Int, item: T)
}