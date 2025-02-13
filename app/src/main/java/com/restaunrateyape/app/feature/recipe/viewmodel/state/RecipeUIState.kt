package com.restaunrateyape.app.feature.recipe.viewmodel.state

import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData

sealed class RecipeUIState {
    data class OnDataReady(val list: List<RecipeData>) : RecipeUIState()
    data class OnDataError(val error: String) : RecipeUIState()
    data object OnLoading : RecipeUIState()
}
