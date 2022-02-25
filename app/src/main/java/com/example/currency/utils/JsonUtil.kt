package com.example.currency.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream


class JsonUtil {
    companion object {
        fun loadJSONFromAsset(ctx: Context, file: String): String {
            var json: String? = null
            json = try {
                val `is`: InputStream = ctx.assets.open(file)
                val size: Int = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, Charsets.UTF_8)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return ""
            }
            return json ?: ""
        }
    }
}