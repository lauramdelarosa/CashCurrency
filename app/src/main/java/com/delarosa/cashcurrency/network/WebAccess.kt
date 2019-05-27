package com.delarosa.cashcurrency.network

import android.util.Log
import retrofit2.Retrofit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * retrofit implementation
 */
object WebAccess {
    private const val BASE_URL = "http://www.apilayer.net/"
    val API: ApiProvider by lazy {
        Log.d("WebAccess", "Creating retrofit client")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Moshi maps JSON to classes
            .addConverterFactory(MoshiConverterFactory.create())
            // The call adapter handles threads
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        // Create Retrofit client
        return@lazy retrofit.create(ApiProvider::class.java)
    }
}