package com.example.currency.di.component

import android.app.Application
import com.example.currency.CurrencyApplication
import com.example.currency.di.module.ActivityBuildersModule
import com.example.currency.di.module.AppModule
import com.example.currency.di.module.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        FragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<CurrencyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}