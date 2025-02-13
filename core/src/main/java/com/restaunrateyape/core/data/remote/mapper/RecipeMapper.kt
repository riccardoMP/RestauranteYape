package com.restaunrateyape.core.data.remote.mapper

import com.restaunrateyape.core.data.local.model.RecipeEntity
import com.restaunrateyape.core.data.remote.model.RecipeDto


internal fun List<RecipeDto>.toRecipeList(): List<RecipeEntity> {
    return this.map {
        it.toRecipe()
    }
}

internal fun RecipeDto.toRecipe(): RecipeEntity {
    return RecipeEntity(
        id = id,
        name = name.orEmpty(),
        image = image.orEmpty(),
        ingredients = ingredients,
        description = description.orEmpty(),
        latitude = origin?.latitude ?: 0.0,
        longitude = origin?.longitude ?: 0.0
    )
}
