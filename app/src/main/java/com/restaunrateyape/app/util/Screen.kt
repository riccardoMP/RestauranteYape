package com.restaunrateyape.app.util

sealed class Screen(val route: String) {

    companion object {
        const val RECIPE_ID: String = "RECIPE_ID"
    }

    data object RecipeScreen : Screen("RecipeScreen")

    data object DetailsScreen : Screen("DetailsScreen/{$RECIPE_ID}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$RECIPE_ID}", newValue = id.toString())
        }
    }

    data object SearchScreen : Screen("SearchScreen")
}
