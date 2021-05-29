package com.entities.ingredientsdetails

data class ModelDetailsIngredient(
    val calories: Int,
    val cautions: List<String>,
    val dietLabels: List<String>,
    val healthLabels: List<String>,
    val totalDaily: TotalDaily,
    val totalNutrients: TotalNutrients,
    val totalNutrientsKCal: TotalNutrientsKCal,
    val totalWeight: Double,
    val uri: String,
    val yield: Double
)