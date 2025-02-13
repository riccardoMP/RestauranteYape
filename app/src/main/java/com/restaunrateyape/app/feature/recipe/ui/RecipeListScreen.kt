package com.restaunrateyape.app.feature.recipe.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeUIState
import com.restaunrateyape.styles.component.topbar.CustomTopAppBar
import com.restaunrateyape.app.R
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeUIState.OnDataError
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeUIState.OnDataReady
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeUIState.OnLoading
import com.restaunrateyape.app.feature.recipe.ui.view.RecipeListContent
import com.restaunrateyape.app.util.Screen
import com.restaunrateyape.styles.component.error.ErrorScreen
import com.restaunrateyape.styles.component.loading.LoadingScreen

@Composable
@ExperimentalMaterial3Api
fun RecipeListScreen(
    navHostController: NavHostController,
    uiState: State<RecipeUIState>,
    lazyListState: LazyListState,
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navHostController,
                title = stringResource(R.string.recipe_title),
                actions = {
                    IconButton(onClick = {
                        val route: String = Screen.SearchScreen.route
                        navHostController.navigate(route)
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                },
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
                    listState = lazyListState,
                    onRecipeClick = onRecipeClick
                )
            }

            is OnDataError -> ErrorScreen(errorMessage = response.error)
            is OnLoading -> LoadingScreen()
        }
    }
}
