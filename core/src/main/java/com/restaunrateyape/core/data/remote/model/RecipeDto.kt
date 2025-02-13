package com.restaunrateyape.core.data.remote.model

import kotlinx.serialization.SerialName

internal data class RecipeDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("ingredients")
    val ingredients: List<String>,
    @SerialName("description")
    val description: String?,
    @SerialName("origin")
    val origin: OriginDto?
)

internal data class OriginDto(
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?
)