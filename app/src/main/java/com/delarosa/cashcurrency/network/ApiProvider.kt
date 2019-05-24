package com.delarosa.cashcurrency.network

import com.delarosa.cashcurrency.model.Currency
import retrofit2.Call
import retrofit2.http.GET

interface ApiProvider {

    @GET("api/live?access_key=8a59ef80dc052235d4654e445f89ff23")
    fun currency(): Call<List<Currency>>

}