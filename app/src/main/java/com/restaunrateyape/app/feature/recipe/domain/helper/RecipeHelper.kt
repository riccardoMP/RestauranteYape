package com.restaunrateyape.app.feature.recipe.domain.helper

import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData
import com.restaunrateyape.core.data.local.model.RecipeEntity


interface RecipeHelper {
    suspend fun loadData(list: List<RecipeEntity>): List<RecipeData>

    suspend fun mapToRecipeDataList(list: List<RecipeEntity>): List<RecipeData>
}