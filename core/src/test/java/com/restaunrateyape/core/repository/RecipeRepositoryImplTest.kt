package com.restaunrateyape.core.repository

import androidx.paging.ExperimentalPagingApi
import com.restaunrateyape.core.data.local.LocalDatabase
import com.restaunrateyape.core.data.local.dao.RecipeDao
import com.restaunrateyape.core.data.local.model.RecipeEntity
import com.restaunrateyape.core.data.remote.ApiService
import com.restaunrateyape.core.data.remote.mapper.toRecipeList
import com.restaunrateyape.core.data.remote.model.RecipeDto
import com.restaunrateyape.core.data.repository.RecipeRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalPagingApi
class RecipeRepositoryImplTest {

    private lateinit var recipeRepository: RecipeRepositoryImpl

    private lateinit var localDatabase: LocalDatabase

    @Mock
    private lateinit var dao: RecipeDao


    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        localDatabase = mock(LocalDatabase::class.java).apply {
            `when`(this.recipeDao).thenReturn(dao)
        }

        recipeRepository =
            RecipeRepositoryImpl(database = localDatabase, apiService = apiService)
    }

    @Test
    fun `Given an id, When getRecipeById is called, Then it should return the RecipeEntity from Database`() =
        runTest {
            val id = 1
            val expectedCharacter = RecipeEntity(
                id = 1,
                name = "Cebiche",
                image = "http://example.com/image.jpg",
                ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3"),
                description = "This is a mock description of the recipe.",
                latitude = 12.345678,
                longitude = 98.765432
            )
            `when`(dao.getById(id)).thenReturn(expectedCharacter)

            val result = recipeRepository.getRecipeById(id)

            assertEquals(expectedCharacter, result)
            verify(dao, never()).insert(expectedCharacter)
        }

    @Test
    fun `When getAllRecipes is called, Then it should fetch recipes from ApiService and update the database`() =
        runTest {
            val recipeDtoList = listOf(mock(RecipeDto::class.java))
            val recipeEntityList = recipeDtoList.toRecipeList()

            `when`(apiService.getRecipes()).thenReturn(recipeDtoList)

            val result = recipeRepository.getAllRecipes()

            assertEquals(recipeEntityList, result)
            verify(dao).clearAndInsertRecipes(recipeEntityList)
        }

    @Test
    fun `Given a query, When searchByNameOrIngredients is called, Then it should return matching RecipeEntities from Database`() =
        runTest {
            val query = "Cebiche"
            val expectedList = listOf(
                RecipeEntity(
                    id = 1,
                    name = "Cebiche",
                    image = "http://example.com/image.jpg",
                    ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3"),
                    description = "This is a mock description of the recipe.",
                    latitude = 12.345678,
                    longitude = 98.765432
                )
            )
            `when`(dao.searchRecipesByNameOrIngredients(query)).thenReturn(expectedList)

            val result = recipeRepository.searchByNameOrIngredients(query)

            assertEquals(expectedList, result)
        }
}
