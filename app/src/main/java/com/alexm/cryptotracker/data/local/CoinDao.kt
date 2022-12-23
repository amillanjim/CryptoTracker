package com.alexm.cryptotracker.data.local

import androidx.room.*

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coin: CoinEntity)

    @Delete
    suspend fun deleteCoin(coin: CoinEntity)

    @Query("""
            SELECT * 
            FROM coins
            """)
    suspend fun observeAllSavedCoins(): List<CoinEntity>

    @Query("""
            SELECT *
            FROM coins 
            WHERE name = :coinName
           """)
    suspend fun observeCoinByName(coinName: String): CoinEntity
}