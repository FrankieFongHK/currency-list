package com.example.currency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.R
import com.example.currency.data.model.CurrencyInfo
import com.example.currency.data.model.Status
import com.example.currency.utils.RecyclerViewCallback
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class CurrencyListFragment : DaggerFragment() {

    private lateinit var adapter: CurrencyAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val currencyViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[CurrencyViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_currency_list, container, false)

        // Set the adapter
        adapter = CurrencyAdapter(listOf())
        adapter.setCallback(activity as RecyclerViewCallback<CurrencyInfo>)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = adapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyViewModel.currencyList.observe(viewLifecycleOwner, Observer { response ->
            if (response.status == Status.SUCCESS) {
                response.data?.let {
                    updateCurrencyList(it)
                }
            }
        })
    }

    private fun updateCurrencyList(currencyList: List<CurrencyInfo>) {
        adapter.update(currencyList)
    }
}