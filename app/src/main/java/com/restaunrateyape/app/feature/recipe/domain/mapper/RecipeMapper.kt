package com.restaunrateyape.app.feature.recipe.domain.mapper

import com.restaunrateyape.app.R
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.Bullet
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.Description
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.ImageUrl
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.TitleRes
import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData
import com.restaunrateyape.core.data.local.model.RecipeEntity

fun RecipeEntity.toRecipeData(): RecipeData {
    return RecipeData(
        id = id,
        name = name,
        imageUrl = image
    )
}

fun RecipeEntity.toRecipeDetailData(): List<RecipeDetailData> {
    return listOf(
        ImageUrl(image),
        RecipeDetailData.TitleString(name),
        Description(description),
        TitleRes(R.string.details_ingredients),
        Bullet(bullets = ingredients),
        TitleRes(R.string.details_location),
        //RowInformation(R.string.details_location, location),
        //Title(R.string.details_origin),
        //RowInformation(R.string.details_origin, origin),
    )
}
