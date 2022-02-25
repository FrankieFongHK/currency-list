package com.example.currency.di.module

import android.app.Application
import com.example.currency.data.db.CurrencyDatabase
import com.example.currency.data.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideCurrencyDatabase(app: Application): CurrencyDatabase = CurrencyDatabase.invoke(app)

    @Provides
    @Singleton
    fun provideCurrencyRepository(db: CurrencyDatabase) = CurrencyRepository(db)

}