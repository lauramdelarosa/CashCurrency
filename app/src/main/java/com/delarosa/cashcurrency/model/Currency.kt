package com.delarosa.cashcurrency.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    val name: String,
    val value: Double,
    val transformValue: Double
)