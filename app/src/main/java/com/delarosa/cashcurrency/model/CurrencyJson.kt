package com.delarosa.cashcurrency.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class  CurrencyJson(
    val quotes: Quotes
)