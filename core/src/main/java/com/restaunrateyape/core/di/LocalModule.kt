package com.restaunrateyape.core.di

import android.content.Context
import androidx.room.Room
import com.restaunrateyape.core.data.local.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class LocalModule {

    @Provides
    @Singleton
    fun provideRecipeDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "restaurantyape.db",
        ).fallbackToDestructiveMigration().build()
    }
}
