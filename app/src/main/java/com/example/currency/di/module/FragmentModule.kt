package com.example.currency.di.module

import com.example.currency.ui.CurrencyListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun provideCurrencyListFragment(): CurrencyListFragment
}