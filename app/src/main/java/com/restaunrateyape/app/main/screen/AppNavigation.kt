package com.restaunrateyape.app.main.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.restaunrateyape.app.feature.details.ui.RecipeDetailsScreen
import com.restaunrateyape.app.feature.details.viewmodel.RecipeDetailsViewModel
import com.restaunrateyape.app.feature.recipe.viewmodel.RecipeListViewModel
import com.restaunrateyape.app.feature.recipe.ui.RecipeListScreen
import com.restaunrateyape.app.feature.recipe.ui.RecipeSearchScreen
import com.restaunrateyape.app.feature.recipe.viewmodel.RecipeSearchViewModel
import com.restaunrateyape.app.util.Screen

@Composable
@ExperimentalMaterial3Api
fun RecipeYapeNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.RecipeScreen.route,
    ) {
        composable(route = Screen.RecipeScreen.route) {
            val viewModel = hiltViewModel<RecipeListViewModel>()

            RecipeListScreen(
                navHostController = navController,
                uiState = viewModel.uiState.collectAsState(),
                lazyListState = viewModel.lazyListState,
            )
        }

        composable(
            route = Screen.DetailsScreen.route,
            arguments = listOf(navArgument(name = Screen.RECIPE_ID) { type = NavType.IntType }),
        ) {
            val viewModel = hiltViewModel<RecipeDetailsViewModel>()
            RecipeDetailsScreen(
                navHostController = navController,
                uiState = viewModel.uiState.collectAsState(),
            )
        }

        composable(route = Screen.SearchScreen.route) {
            val viewModel = hiltViewModel<RecipeSearchViewModel>()

            RecipeSearchScreen(
                navHostController = navController,
                uiState = viewModel.uiState.collectAsState(),
                searchQuery = viewModel.searchQuery.collectAsState().value,
                onValueChange = viewModel::updateSearchQuery,
                navigateUp = navController::navigateUp,
            )
        }
    }
}
