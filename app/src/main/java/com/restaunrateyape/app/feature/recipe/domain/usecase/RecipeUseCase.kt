package com.restaunrateyape.app.feature.recipe.domain.usecase

import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {
    suspend fun loadData(): Flow<RecipeStateDomain>
}
