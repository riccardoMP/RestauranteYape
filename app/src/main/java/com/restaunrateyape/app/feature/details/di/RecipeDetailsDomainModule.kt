package com.restaunrateyape.app.feature.details.di

import com.restaunrateyape.app.feature.details.domain.usecase.RecipeDetailsUseCase
import com.restaunrateyape.app.feature.details.domain.usecase.RecipeDetailsUseCaseImpl
import com.restaunrateyape.app.feature.recipe.domain.helper.RecipeHelper
import com.restaunrateyape.core.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeDetailsDomainModule {
    @Provides
    @Singleton
    fun providesRecipeDetailsUseCase(
        repository: RecipeRepository,
        helper: RecipeHelper
    ): RecipeDetailsUseCase =
        RecipeDetailsUseCaseImpl(repository = repository)
}
