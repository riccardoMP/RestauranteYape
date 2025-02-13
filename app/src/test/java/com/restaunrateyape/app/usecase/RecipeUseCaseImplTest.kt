package com.restaunrateyape.app.usecase

import com.restaunrateyape.app.feature.recipe.domain.helper.RecipeHelper
import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData
import com.restaunrateyape.app.feature.recipe.domain.usecase.RecipeUseCaseImpl
import com.restaunrateyape.app.feature.recipe.domain.usecase.state.RecipeStateDomain
import com.restaunrateyape.core.data.local.model.RecipeEntity
import com.restaunrateyape.core.data.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RecipeUseCaseImplTest {

    private lateinit var recipeUseCase: RecipeUseCaseImpl

    @Mock
    private lateinit var repository: RecipeRepository

    @Mock
    private lateinit var helper: RecipeHelper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        recipeUseCase = RecipeUseCaseImpl(repository, helper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When loadData is called, Then it should emit Loading and DataReady states`() = runTest {
        val recipeEntities = listOf(
            RecipeEntity(id = 1, name = "A", image = "image1.jpg", ingredients = listOf(), description = "", latitude = 0.0, longitude = 0.0)
        )
        val recipeData = listOf(
            RecipeData(id = 1, name = "A", imageUrl = "image1.jpg")
        )

        `when`(repository.getAllRecipes()).thenReturn(recipeEntities)
        `when`(helper.loadData(recipeEntities)).thenReturn(recipeData)

        val flow = recipeUseCase.loadData()

        val states = flow.toList()

        assertEquals(RecipeStateDomain.Loading, states[0])
        assertEquals(RecipeStateDomain.DataReady(recipeData), states[1])
    }

    @Test
    fun `When loadData is called with empty data, Then it should emit Loading and DataError states`() = runTest {
        `when`(repository.getAllRecipes()).thenReturn(emptyList())

        val flow = recipeUseCase.loadData()

        val states = flow.toList()

        assertEquals(RecipeStateDomain.Loading, states[0])
        assertEquals(RecipeStateDomain.DataError("Error al cargar las recetas"), states[1])
    }

    @Test
    fun `When searchByNameOrIngredients is called, Then it should emit Loading and DataReady states`() = runTest {
        val query = "A"
        val recipeEntities = listOf(
            RecipeEntity(id = 1, name = "A", image = "image1.jpg", ingredients = listOf(), description = "", latitude = 0.0, longitude = 0.0)
        )
        val recipeData = listOf(
            RecipeData(id = 1, name = "A", imageUrl = "image1.jpg")
        )

        `when`(repository.searchByNameOrIngredients(query)).thenReturn(recipeEntities)
        `when`(helper.mapToRecipeDataList(recipeEntities)).thenReturn(recipeData)

        val flow = recipeUseCase.searchByNameOrIngredients(query)

        val states = flow.toList()

        assertEquals(RecipeStateDomain.Loading, states[0])
        assertEquals(RecipeStateDomain.DataReady(recipeData), states[1])
    }

    @Test
    fun `When searchByNameOrIngredients is called with no results, Then it should emit Loading and DataError states`() = runTest {
        val query = "A"
        `when`(repository.searchByNameOrIngredients(query)).thenReturn(emptyList())

        val flow = recipeUseCase.searchByNameOrIngredients(query)

        val states = flow.toList()

        assertEquals(RecipeStateDomain.Loading, states[0])
        assertEquals(RecipeStateDomain.DataError("Error al cargar la búsqueda"), states[1])
    }
}