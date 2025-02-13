package com.restaunrateyape.app.feature.recipe.domain.usecase

import com.restaunrateyape.app.feature.recipe.domain.helper.RecipeHelper
import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain
import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain.DataError
import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain.DataReady
import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain.Loading
import com.restaunrateyape.core.data.local.model.RecipeEntity
import com.restaunrateyape.core.data.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class RecipeUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository,
    private val helper: RecipeHelper,
) : RecipeUseCase {

    override suspend fun loadData(): Flow<RecipeStateDomain> = flow {
        emit(Loading)

        val entityList: List<RecipeEntity> = repository.getAllRecipes()

        if (entityList.isNotEmpty()) {
            val recipeDataList = helper.loadData(entityList)
            emit(DataReady(list = recipeDataList))
        } else {
            emit(DataError("Error al cargar las recetas"))
        }
    }.flowOn(Dispatchers.IO)
}
