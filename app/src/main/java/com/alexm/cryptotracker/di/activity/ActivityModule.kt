package com.alexm.cryptotracker.di.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideAppCompatActivity(activity: Activity): AppCompatActivity {
        return activity as AppCompatActivity
    }

    @Provides
    fun provideFragmentManager(activity: AppCompatActivity): FragmentManager =
        activity.supportFragmentManager
}