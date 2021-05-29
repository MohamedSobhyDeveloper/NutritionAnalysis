package com.entities.ingredients

data class ModelIngredientsRequest(
    val ingr: List<String>,
    val prep: String,
    val title: String,
    val yield: String
)