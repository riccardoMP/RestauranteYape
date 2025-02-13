package com.restaunrateyape.app.feature.details.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.restaunrateyape.app.R
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeDetailsUIState
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeDetailsUIState.OnDataError
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeDetailsUIState.OnDetailsReady
import com.restaunrateyape.app.feature.details.viewmodel.state.RecipeDetailsUIState.OnLoading
import com.restaunrateyape.app.util.Screen
import com.restaunrateyape.styles.component.error.ErrorScreen
import com.restaunrateyape.styles.component.loading.LoadingScreen
import com.restaunrateyape.styles.component.topbar.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreen(
    navHostController: NavHostController,
    uiState: State<RecipeDetailsUIState>,
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navHostController,
                title = stringResource(R.string.details_title),
            )
        },
    ) { innerPadding ->

        val onButtonClick: (Pair<Double, Double>) -> Unit = {
            val route: String = Screen.MapsScreen.passId(
                latitude = it.first,
                longitude = it.second
            )
            navHostController.navigate(route)
        }

        when (val response = uiState.value) {
            is OnDetailsReady ->
                RecipeDetailsContent(
                    list = response.data,
                    modifier = Modifier.padding(innerPadding),
                    onButtonClick = onButtonClick
                )

            is OnDataError -> ErrorScreen(errorMessage = response.error)
            is OnLoading -> LoadingScreen()
        }
    }
}
