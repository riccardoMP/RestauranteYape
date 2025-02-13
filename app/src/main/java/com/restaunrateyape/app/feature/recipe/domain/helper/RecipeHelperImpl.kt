package com.restaunrateyape.app.feature.recipe.domain.helper

import com.restaunrateyape.app.feature.recipe.domain.mapper.toRecipeData
import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData
import com.restaunrateyape.core.data.local.model.RecipeEntity
import javax.inject.Inject

class RecipeHelperImpl @Inject constructor() : RecipeHelper {
    override suspend fun loadData(list: List<RecipeEntity>): List<RecipeData> {
        return list
            .sortedWith(compareBy { it.name })
            .map { it.toRecipeData() }
    }
}