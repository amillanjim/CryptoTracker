package com.alexm.cryptotracker.di.app

import com.alexm.cryptotracker.di.qualifiers.GsonBase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GsonModule {

    @Binds
    @Singleton
    @GsonBase
    abstract fun bindGson(gson: Gson): Gson

    companion object {
        @Provides
        @Singleton
        fun provideGsonBuilder(): Gson =
            GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
    }
}