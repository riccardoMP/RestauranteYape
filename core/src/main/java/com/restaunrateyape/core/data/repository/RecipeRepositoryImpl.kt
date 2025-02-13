package com.restaunrateyape.core.data.repository

import com.restaunrateyape.core.data.local.LocalDatabase
import com.restaunrateyape.core.data.local.model.RecipeEntity
import com.restaunrateyape.core.data.remote.ApiService
import com.restaunrateyape.core.data.remote.mapper.toRecipeList
import javax.inject.Inject

internal class RecipeRepositoryImpl @Inject constructor(
    private val database: LocalDatabase,
    private val apiService: ApiService,
) : RecipeRepository {
    override suspend fun getAllRecipes(): List<RecipeEntity> {
        return try {
            val list: List<RecipeEntity> = apiService.getRecipes().toRecipeList()
            database.recipeDao.clearAndInsertRecipes(list)
            list
        } catch (exception: Exception) {
            emptyList()
        }
    }

    override suspend fun getRecipeById(id: Int): RecipeEntity? {
        val entity: RecipeEntity? = database.recipeDao.getById(id)

        return entity
    }
}
