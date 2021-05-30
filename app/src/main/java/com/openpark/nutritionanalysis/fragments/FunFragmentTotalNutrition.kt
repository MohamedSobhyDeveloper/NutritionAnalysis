package com.openpark.nutritionanalysis.fragments

import android.annotation.SuppressLint
import com.entities.ingredientsdetails.ModelDetailsIngredient


@SuppressLint("SetTextI18n")
fun FragmentTotalNutrition.initialize(savedData: ModelDetailsIngredient) {
    binding.tvCalories.text = "Calories :- " + savedData.calories.toString()
    binding.tvCalories.text =
        "Fat :- " + savedData.totalNutrients.FAT.label + " " + savedData.totalNutrients.FAT.unit
    binding.tvCalories.text =
        "Cholesterol :- " + savedData.totalNutrients.CHOLE.label + " " + savedData.totalNutrients.CHOLE.unit
    binding.tvCalories.text =
        "Sodium :- " + savedData.totalNutrients.NA.label + " " + savedData.totalNutrients.NA.unit
    val Carbohydrate =
        savedData.totalNutrients.FIBTG.quantity + savedData.totalNutrients.SUGAR.quantity
    binding.tvCalories.text = "Carbohydrate :- $Carbohydrate g"
    binding.tvCalories.text =
        "Protein :- " + savedData.totalNutrients.PROCNT.label + " " + savedData.totalNutrients.PROCNT.unit
    binding.tvCalories.text =
        "Vitamin :- " + savedData.totalNutrients.VITC.label + " " + savedData.totalNutrients.VITC.unit
    binding.tvCalories.text =
        "Calcium :- " + savedData.totalNutrients.CA.label + " " + savedData.totalNutrients.CA.unit
    binding.tvCalories.text =
        "Iron :- " + savedData.totalNutrients.FE.label + " " + savedData.totalNutrients.FE.unit
    binding.tvCalories.text =
        "Potassium :- " + savedData.totalNutrients.K.label + " " + savedData.totalNutrients.K.unit


}