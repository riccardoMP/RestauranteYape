package com.restaunrateyape.app.feature.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restaunrateyape.app.feature.details.domain.usecase.RecipeDetailsUseCase
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain.DataError
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain.DetailsReady
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain.Loading
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeDetailsUIState
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeDetailsUIState.OnDataError
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeDetailsUIState.OnDetailsReady
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeDetailsUIState.OnLoading
import com.restaunrateyape.app.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: RecipeDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeDetailsUIState>(OnLoading)

    val uiState: StateFlow<RecipeDetailsUIState> = _uiState
        .flatMapLatest {
            val id: Int =
                savedStateHandle.get<Int>(Screen.RECIPE_ID) ?: return@flatMapLatest flowOf(
                    OnDataError("Recipe ID not found"),
                )

            useCase.loadData(id).map { result ->
                when (result) {
                    is Loading -> OnLoading
                    is DetailsReady -> OnDetailsReady(result.data)
                    is DataError -> OnDataError(result.error)
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OnLoading)
}
