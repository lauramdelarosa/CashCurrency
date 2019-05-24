package com.delarosa.cashcurrency.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    @field:Json(name = "quotes") val name: Pair<String, Double>
)