package com.restaunrateyape.app.feature.recipe.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.restaunrateyape.app.feature.recipe.viewmodel.state.RecipeUIState
import com.restaunrateyape.styles.component.searchbar.CustomSearchBar
import com.restaunrateyape.app.R
import com.restaunrateyape.app.feature.recipe.viewmodel.state.RecipeUIState.OnDataError
import com.restaunrateyape.app.feature.recipe.viewmodel.state.RecipeUIState.OnDataReady
import com.restaunrateyape.app.feature.recipe.viewmodel.state.RecipeUIState.OnLoading
import com.restaunrateyape.app.feature.recipe.ui.view.RecipeListContent
import com.restaunrateyape.app.util.Screen
import com.restaunrateyape.styles.component.error.ErrorScreen
import com.restaunrateyape.styles.component.loading.LoadingScreen

@Composable
@ExperimentalMaterial3Api
fun RecipeSearchScreen(
    navHostController: NavHostController,
    uiState: State<RecipeUIState>,
    searchQuery: String,
    onValueChange: (String) -> Unit,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            CustomSearchBar (
                value = searchQuery,
                placeholder = stringResource(R.string.custom_search_bar_placeholder),
                navigateUp = navigateUp,
                onValueChange = onValueChange,
            )
        },
    ) { innerPadding ->

        val onRecipeClick: (Int) -> Unit = { id ->
            val route: String = Screen.DetailsScreen.passId(id = id)
            navHostController.navigate(route)
        }

        when (val response = uiState.value) {
            is OnDataReady -> {
                RecipeListContent(
                    list = response.list,
                    modifier = Modifier.padding(innerPadding),
                    onRecipeClick = onRecipeClick
                )
            }

            is OnDataError -> ErrorScreen(errorMessage = response.error)
            is OnLoading -> LoadingScreen()
        }
    }
}
