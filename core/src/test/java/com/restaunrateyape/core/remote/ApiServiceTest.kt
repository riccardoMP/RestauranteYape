package com.restaunrateyape.core.remote

import com.restaunrateyape.core.data.remote.ApiService
import com.restaunrateyape.core.data.remote.model.RecipeDto
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ApiServiceTest {

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `Given an apiService, When getRecipes is called, Then it should return a list of RecipeDto`() {
        runBlocking {
            val dtoList = listOf(mock(RecipeDto::class.java))

            doReturn(dtoList).`when`(apiService).getRecipes()

            val result = apiService.getRecipes()

            assertEquals(dtoList, result)
            verify(apiService).getRecipes()
        }
    }
}
