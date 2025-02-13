package com.restaunrateyape.app.usecase

import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData
import com.restaunrateyape.app.feature.details.domain.usecase.RecipeDetailsUseCaseImpl
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain
import com.restaunrateyape.core.data.local.model.RecipeEntity
import com.restaunrateyape.core.data.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
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
import com.restaunrateyape.app.R
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.Bullet
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.Description
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.ImageUrl
import com.restaunrateyape.app.feature.details.domain.model.RecipeDetailData.TitleRes
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain.DataError
import com.restaunrateyape.app.feature.details.domain.usecase.state.RecipeDetailsStateDomain.Loading
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class RecipeDetailsUseCaseImplTest {

    private lateinit var recipeDetailsUseCase: RecipeDetailsUseCaseImpl

    @Mock
    private lateinit var repository: RecipeRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        recipeDetailsUseCase = RecipeDetailsUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When loadData is called with a valid id, Then it should emit Loading and DetailsReady states`() =
        runTest {
            val id = 1
            val recipeEntity = RecipeEntity(
                id = 1,
                name = "A",
                image = "image1.jpg",
                ingredients = listOf("Ingredient 1", "Ingredient 2"),
                description = "Description",
                latitude = 0.0,
                longitude = 0.0
            )
            val recipeDetailData = listOf(
                ImageUrl(recipeEntity.image),
                RecipeDetailData.TitleString(recipeEntity.name),
                Description(recipeEntity.description),
                TitleRes(R.string.details_ingredients),
                Bullet(bullets = recipeEntity.ingredients),
                TitleRes(R.string.details_location),
            )

            `when`(repository.getRecipeById(id)).thenReturn(recipeEntity)

            val flow = recipeDetailsUseCase.loadData(id)

            val states = flow.toList()

            assertEquals(Loading, states[0])
            assertEquals(RecipeDetailsStateDomain.DetailsReady(recipeDetailData), states[1])
        }

    @Test
    fun `When loadData is called with an invalid id, Then it should emit Loading and DataError states`() =
        runTest {
            val id = 1

            `when`(repository.getRecipeById(id)).thenReturn(null)

            val flow = recipeDetailsUseCase.loadData(id)

            val states = flow.toList()

            assertEquals(Loading, states[0])
            assertEquals(DataError("Error"), states[1])
        }
}