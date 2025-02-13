package com.restaunrateyape.core.di

import com.restaunrateyape.core.data.local.LocalDatabase
import com.restaunrateyape.core.data.remote.ApiService
import com.restaunrateyape.core.data.repository.RecipeRepository
import com.restaunrateyape.core.data.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {

    @Provides
    @Singleton
    fun providesRecipeRepository(
        database: LocalDatabase,
        apiService: ApiService,
    ): RecipeRepository =
        RecipeRepositoryImpl(database = database, apiService = apiService)
}
