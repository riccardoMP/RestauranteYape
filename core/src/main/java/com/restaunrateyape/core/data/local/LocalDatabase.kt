package com.restaunrateyape.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.restaunrateyape.core.data.local.converter.RecipeConverter
import com.restaunrateyape.core.data.local.dao.RecipeDao
import com.restaunrateyape.core.data.local.model.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(RecipeConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
}
