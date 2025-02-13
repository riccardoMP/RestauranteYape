package com.restaunrateyape.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RecipeEntity.TABLE_NAME)
data class RecipeEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("image")
    val image: String,
    @ColumnInfo("ingredients")
    val ingredients: List<String>,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("latitude")
    val latitude: Double,
    @ColumnInfo("longitude")
    val longitude: Double

) {
    companion object {
        const val TABLE_NAME = "recipe_table"
    }
}
