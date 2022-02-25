package com.example.currency.di.module

import com.example.currency.ui.DemoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun bindDemoActivity(): DemoActivity
}