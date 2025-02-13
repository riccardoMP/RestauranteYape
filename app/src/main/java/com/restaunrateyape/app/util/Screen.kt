package com.restaunrateyape.app.util

sealed class Screen(val route: String) {

    companion object {
        const val RECIPE_ID: String = "RECIPE_ID"
        const val LATITUDE: String = "LATITUDE"
        const val LONGITUDE: String = "LONGITUDE"
    }

    data object RecipeScreen : Screen("RecipeScreen")

    data object DetailsScreen : Screen("DetailsScreen/{$RECIPE_ID}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$RECIPE_ID}", newValue = id.toString())
        }
    }

    data object MapsScreen : Screen("MapsScreen/{$LATITUDE}/{$LONGITUDE}") {
        fun passId(latitude: Double, longitude: Double): String {
            return this.route
                .replace(oldValue = "{$LATITUDE}", newValue = latitude.toString())
                .replace(oldValue = "{$LONGITUDE}", newValue = longitude.toString())
        }
    }

    data object SearchScreen : Screen("SearchScreen")
}
