package com.delarosa.cashcurrency.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntity::class], version = 1)
public abstract class CurrencyRoomDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}