package com.restaunrateyape.app.feature.details.domain.usecase

import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain.DataError
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain.DetailsReady
import com.restaunrateyape.app.feature.recipe.domain.mapper.toRecipeDetailData
import com.restaunrateyape.core.data.local.model.RecipeEntity
import com.restaunrateyape.core.data.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class RecipeDetailsUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository,
) : RecipeDetailsUseCase {

    override suspend fun loadData(id: Int): Flow<RecipeDetailsStateDomain> = flow {
        emit(RecipeDetailsStateDomain.Loading)

        val data: RecipeEntity? = repository.getRecipeById(id)

        if (data != null) {
            val characterDetailData: List<RecipeDetailData> = data.toRecipeDetailData()
            emit(DetailsReady(characterDetailData))
        } else {
            emit(DataError("Error"))
        }
    }.flowOn(Dispatchers.IO)
}
