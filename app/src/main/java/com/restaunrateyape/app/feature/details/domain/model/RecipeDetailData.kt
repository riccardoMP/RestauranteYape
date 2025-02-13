package com.restaunrateyape.app.feature.details.domain.model

import androidx.annotation.StringRes

sealed class RecipeDetailData {
    data class TitleRes(@StringRes val title: Int) : RecipeDetailData()
    data class TitleString(val title: String) : RecipeDetailData()
    data class ImageUrl(val imageUrl: String) : RecipeDetailData()
    data class RowInformation(@StringRes val title: Int, val value: String) : RecipeDetailData()
    data class Description(val value: String) : RecipeDetailData()
    data class Bullet(val bullets: List<String>) : RecipeDetailData()
    data class ButtonData(@StringRes val value: Int, val latitude: Double, val longitude: Double) :
        RecipeDetailData()

    data object Divider : RecipeDetailData()
}
