package com.restaunrateyape.app.feature.details.domain.usecase.state

import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData


sealed interface RecipeDetailsStateDomain {
    data class DetailsReady(val data: List<RecipeDetailData>) : RecipeDetailsStateDomain
    data class DataError(val error: String) : RecipeDetailsStateDomain
    data object Loading : RecipeDetailsStateDomain
}
