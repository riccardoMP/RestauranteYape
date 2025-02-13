package com.restaunrateyape.core.data.repository

import com.restaunrateyape.core.data.local.model.RecipeEntity

interface RecipeRepository {

    suspend fun getAllRecipes(): List<RecipeEntity>
    suspend fun getRecipeById(id: Int): RecipeEntity?
    suspend fun searchByNameOrIngredients(query: String): List<RecipeEntity>
}
