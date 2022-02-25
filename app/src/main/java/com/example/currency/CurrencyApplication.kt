package com.example.currency

import android.util.Log
import com.example.currency.di.component.AppComponent
import com.example.currency.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CurrencyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)

        return component
    }

}