package com.restaunrateyape.app.feature.recipe.domain.usecase.state

import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData

sealed interface RecipeStateDomain {
    data class DataReady(val list: List<RecipeData>) : RecipeStateDomain
    data class DataError(val error: String) : RecipeStateDomain
    data object Loading : RecipeStateDomain
}
