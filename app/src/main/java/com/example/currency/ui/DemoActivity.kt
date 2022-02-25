package com.example.currency.ui

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.currency.R
import com.example.currency.data.model.CurrencyInfo
import com.example.currency.databinding.ActivityDemoBinding
import com.example.currency.utils.RecyclerViewCallback
import com.jakewharton.rxbinding3.view.clicks
import dagger.android.support.DaggerAppCompatActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class DemoActivity : DaggerAppCompatActivity(), RecyclerViewCallback<CurrencyInfo> {

    lateinit var binding: ActivityDemoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val currencyViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(CurrencyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo)

        binding.btnLoadCurrency.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe { currencyViewModel.getCurrencyList() }

        binding.btnSort.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe { currencyViewModel.sort() }
    }

    override fun onItemSelected(position: Int, item: CurrencyInfo) {
        Toast.makeText(this, "Clicked: " + item.name, Toast.LENGTH_SHORT).show()
    }
}