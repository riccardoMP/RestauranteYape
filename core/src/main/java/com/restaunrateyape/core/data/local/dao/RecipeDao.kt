package com.restaunrateyape.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.restaunrateyape.core.data.local.model.RecipeEntity

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RecipeEntity)

    @Query("SELECT * FROM ${RecipeEntity.TABLE_NAME} WHERE id=:id")
    suspend fun getById(id: Int): RecipeEntity?


    @Query("DELETE FROM ${RecipeEntity.TABLE_NAME}")
    suspend fun clearAll()

    @Query(
        """
        SELECT * FROM ${RecipeEntity.TABLE_NAME} 
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' 
        OR LOWER(ingredients) LIKE '%' || LOWER(:query) || '%' 
        ORDER BY name ASC
    """
    )
    fun searchRecipesByNameOrIngredients(query: String): List<RecipeEntity>

    @Transaction
    suspend fun clearAndInsertRecipes(items: List<RecipeEntity>) {
        clearAll()
        insertAll(items)
    }

}
