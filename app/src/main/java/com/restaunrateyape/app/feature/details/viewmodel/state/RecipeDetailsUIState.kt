package com.restaunrateyape.app.feature.details.viewmodel.state

import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData


sealed class RecipeDetailsUIState {
    data class OnDetailsReady(val data: List<RecipeDetailData>) : RecipeDetailsUIState()
    data class OnDataError(val error: String) : RecipeDetailsUIState()
    data object OnLoading : RecipeDetailsUIState()
}
