package com.restaunrateyape.app.feature.details.domain.usecase

import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain
import kotlinx.coroutines.flow.Flow

interface RecipeDetailsUseCase {
    suspend fun loadData(id: Int): Flow<RecipeDetailsStateDomain>
}
