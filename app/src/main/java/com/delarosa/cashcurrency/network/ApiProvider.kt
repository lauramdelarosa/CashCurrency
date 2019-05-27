package com.delarosa.cashcurrency.network

import com.delarosa.cashcurrency.model.CurrencyJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiProvider {

    @GET("api/live?access_key=8a59ef80dc052235d4654e445f89ff23")
    fun currency(): Deferred<Response<CurrencyJson>>

}