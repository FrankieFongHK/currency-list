package com.example.currency.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currency.data.model.CurrencyInfo
import io.reactivex.rxjava3.core.Observable

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CurrencyInfo)

    @Query("SELECT * FROM currency")
    fun getCurrencyList(): Observable<List<CurrencyInfo>>
}