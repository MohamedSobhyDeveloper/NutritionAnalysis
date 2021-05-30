package com.openpark.nutritionanalysis.fragments

import android.annotation.SuppressLint
import android.view.View
import com.entities.ingredientsdetails.ModelDetailsIngredient


@SuppressLint("SetTextI18n")
fun FragmentTotalNutrition.initialize(savedData: ModelDetailsIngredient) {
    binding.tvCalories.text = "Calories :- " + savedData.calories.toString()
    binding.tvFat.text =
        "Fat :- " + savedData.totalNutrients.FAT.quantity + " " + savedData.totalNutrients.FAT.unit
    binding.tvCholesterol.text =
        "Cholesterol :- " + savedData.totalNutrients.CHOLE.quantity + " " + savedData.totalNutrients.CHOLE.unit
    binding.tvSodium.text =
        "Sodium :- " + savedData.totalNutrients.NA.quantity + " " + savedData.totalNutrients.NA.unit
    if (savedData.totalNutrients.FIBTG!=null&&savedData.totalNutrients.SUGAR!=null){
        val Carbohydrate =
            savedData.totalNutrients.FIBTG?.quantity + savedData.totalNutrients.SUGAR?.quantity
        binding.tvCarbohydrate.text = "Carbohydrate :- $Carbohydrate g"
    }else{
        binding.tvCarbohydrate.visibility=View.GONE
    }


    binding.tvProtein.text =
        "Protein :- " + savedData.totalNutrients.PROCNT.quantity + " " + savedData.totalNutrients.PROCNT.unit
    binding.tvVitamin.text =
        "Vitamin :- " + savedData.totalNutrients.VITC.quantity + " " + savedData.totalNutrients.VITC.unit
    binding.tvCalcium.text =
        "Calcium :- " + savedData.totalNutrients.CA.quantity + " " + savedData.totalNutrients.CA.unit
    binding.tvIron.text =
        "Iron :- " + savedData.totalNutrients.FE.quantity + " " + savedData.totalNutrients.FE.unit
    binding.tvPotassium.text =
        "Potassium :- " + savedData.totalNutrients.K.quantity + " " + savedData.totalNutrients.K.unit


}