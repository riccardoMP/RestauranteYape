package com.restaunrateyape.core.data.remote

import com.restaunrateyape.core.data.remote.model.RecipeDto
import retrofit2.http.GET

internal interface ApiService {
    @GET("recipes")
    suspend fun getRecipes(): List<RecipeDto>
}
