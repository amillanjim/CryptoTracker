package com.alexm.cryptotracker.di.app

import android.app.Application
import com.alexm.cryptotracker.base.BaseDBConfiguration
import com.alexm.cryptotracker.data.local.CoinDatabase
import com.alexm.cryptotracker.data.repository.CoinRepositoryImpl
import com.alexm.cryptotracker.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoDataModule{

    @Binds
    @Singleton
    abstract fun bindCoinRepository(coinRepository: CoinRepositoryImpl): CoinRepository

    companion object {
        @Provides
        @Singleton
        fun provideCoinDatabase(app: Application): CoinDatabase =
            BaseDBConfiguration().invoke(app) as CoinDatabase

        @Provides
        @Singleton
        fun provideCoinDao(database: CoinDatabase) = database.coinDao()
    }
}