package com.delarosa.cashcurrency.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Query("SELECT * from currency_table ORDER BY name ASC")
    fun getAll(): LiveData<List<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyEntity: CurrencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(currency: List<CurrencyEntity>)

    @Query("DELETE FROM currency_table")
    fun deleteAll()
}