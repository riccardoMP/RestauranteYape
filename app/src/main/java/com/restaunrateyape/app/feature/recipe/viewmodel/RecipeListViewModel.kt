package com.restaunrateyape.app.feature.recipe.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restaunrateyape.app.feature.recipe.domain.usecase.RecipeUseCase
import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain.DataError
import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain.DataReady
import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain.Loading
import com.restaunrateyape.app.feature.recipe.viewmodel.state.RecipeUIState
import com.restaunrateyape.app.feature.recipe.viewmodel.state.RecipeUIState.OnDataError
import com.restaunrateyape.app.feature.recipe.viewmodel.state.RecipeUIState.OnDataReady
import com.restaunrateyape.app.feature.recipe.viewmodel.state.RecipeUIState.OnLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val useCase: RecipeUseCase,
) : ViewModel() {

    val lazyListState = LazyListState()
    private val _uiState = MutableStateFlow<RecipeUIState>(OnLoading)

    val uiState: StateFlow<RecipeUIState> = _uiState
        .flatMapLatest {
            useCase.loadData().map { result ->
                when (result) {
                    is Loading -> OnLoading
                    is DataReady -> OnDataReady(result.list)
                    is DataError -> OnDataError(result.error)
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OnLoading)
}
