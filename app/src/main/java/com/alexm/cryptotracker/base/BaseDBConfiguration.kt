package com.alexm.cryptotracker.base

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.data.local.CoinDatabase

class BaseDBConfiguration {
    operator fun invoke(app: Application): RoomDatabase{
        return Room.databaseBuilder(
            app,
            CoinDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }
}