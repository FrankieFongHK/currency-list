package com.example.currency.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.currency.data.model.CurrencyInfo
import com.example.currency.utils.JsonUtil
import org.json.JSONArray


@Database(entities = [CurrencyInfo::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var instance: CurrencyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(ctx: Context) =
            Room.databaseBuilder(ctx, CurrencyDatabase::class.java, "CurrencyDatabase.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        val currencyStr = JsonUtil.loadJSONFromAsset(ctx, "currency.json")
                        val currencyArray = JSONArray(currencyStr)

                        // insert into local database
                        if (currencyArray.length() > 0) {
                            for (i in 0 until currencyArray.length()) {
                                val jsonObject = currencyArray.getJSONObject(i)
                                jsonObject.apply {
                                    val currency = CurrencyInfo(
                                        getString("id"),
                                        getString("name"),
                                        getString("symbol")
                                    )
                                    Thread {
                                        instance?.getCurrencyDao()?.insert(currency)
                                    }.start()
                                }
                            }
                        }
                    }
                }).build()
    }
}