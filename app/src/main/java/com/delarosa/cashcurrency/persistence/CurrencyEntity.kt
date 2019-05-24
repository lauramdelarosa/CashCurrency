package com.delarosa.cashcurrency.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "transform_value") val transformValue: Double
)

