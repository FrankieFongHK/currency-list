package com.example.currency.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.currency.data.repository.CurrencyRepository
import com.example.currency.data.model.APIResponse
import com.example.currency.data.model.CurrencyInfo
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(private val repository: CurrencyRepository) :
    ViewModel() {

    var currencyList: LiveData<APIResponse<List<CurrencyInfo>>>
        private set

    init {
        currencyList = repository.currencyList
    }

    fun getCurrencyList() = repository.getCurrencyList()
    fun sort() = repository.sort()

    override fun onCleared() {
        super.onCleared()

        repository.clear()
    }
}