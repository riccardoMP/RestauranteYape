package com.restaunrateyape.app.feature.recipe.domain.mapper

import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData
import com.restaunrateyape.core.data.local.model.RecipeEntity

fun RecipeEntity.toRecipeData(): RecipeData {
    return RecipeData(
        id = id,
        name = name,
        imageUrl = image
    )
}
