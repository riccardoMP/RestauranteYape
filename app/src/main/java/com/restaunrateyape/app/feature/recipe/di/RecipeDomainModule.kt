package com.restaunrateyape.app.feature.recipe.di

import com.restaunrateyape.app.feature.recipe.domain.helper.RecipeHelper
import com.restaunrateyape.app.feature.recipe.domain.helper.RecipeHelperImpl
import com.restaunrateyape.app.feature.recipe.domain.usecase.RecipeUseCase
import com.restaunrateyape.app.feature.recipe.domain.usecase.RecipeUseCaseImpl
import com.restaunrateyape.core.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeDomainModule {

    @Provides
    @Singleton
    fun providesRecipeHelper(): RecipeHelper =
        RecipeHelperImpl()

    @Provides
    @Singleton
    fun providesRecipeUseCase(repository: RecipeRepository, helper: RecipeHelper): RecipeUseCase =
        RecipeUseCaseImpl(repository = repository, helper = helper)
}
