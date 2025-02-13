package com.restaunrateyape.app.helper

import com.restaunrateyape.app.feature.recipe.domain.helper.RecipeHelperImpl
import com.restaunrateyape.app.feature.recipe.domain.model.RecipeData
import com.restaunrateyape.core.data.local.model.RecipeEntity
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class RecipeHelperImplTest {

    private lateinit var helper: RecipeHelperImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        helper = RecipeHelperImpl()
    }

    @Test
    fun `Given a list of RecipeEntity, When loadData is called, Then it should return a sorted list of RecipeData`() =
        runTest {
            val recipeEntities = listOf(
                RecipeEntity(
                    id = 2,
                    name = "B",
                    image = "image2.jpg",
                    ingredients = listOf(),
                    description = "",
                    latitude = 0.0,
                    longitude = 0.0
                ),
                RecipeEntity(
                    id = 1,
                    name = "A",
                    image = "image1.jpg",
                    ingredients = listOf(),
                    description = "",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )

            val expectedRecipeData = listOf(
                RecipeData(id = 1, name = "A", imageUrl = "image1.jpg"),
                RecipeData(id = 2, name = "B", imageUrl = "image2.jpg")
            )

            val result = helper.loadData(recipeEntities)

            assertEquals(expectedRecipeData, result)
        }

    @Test
    fun `Given a list of RecipeEntity, When mapToRecipeDataList is called, Then it should return a list of RecipeData`() =
        runTest {
            val recipeEntities = listOf(
                RecipeEntity(
                    id = 1,
                    name = "A",
                    image = "image1.jpg",
                    ingredients = listOf(),
                    description = "",
                    latitude = 0.0,
                    longitude = 0.0
                ),
                RecipeEntity(
                    id = 2,
                    name = "B",
                    image = "image2.jpg",
                    ingredients = listOf(),
                    description = "",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )

            val expectedRecipeData = listOf(
                RecipeData(id = 1, name = "A", imageUrl = "image1.jpg"),
                RecipeData(id = 2, name = "B", imageUrl = "image2.jpg")
            )

            val result = helper.mapToRecipeDataList(recipeEntities)

            assertEquals(expectedRecipeData, result)
        }
}
