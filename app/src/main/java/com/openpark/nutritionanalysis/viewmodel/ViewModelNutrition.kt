package com.openpark.nutritionanalysis.viewmodel


import androidx.lifecycle.ViewModel
import com.entities.ingredients.ModelIngredientsRequest
import com.usecase.network.RestClient
import com.usecase.network.loadDataGeneric


class ViewModelNutrition :ViewModel() {

    fun apiIngredientDetails(data:ModelIngredientsRequest,appId:String,appKey:String) = loadDataGeneric{

        RestClient.apiService.getNutritionDetails(data,appId,appKey)
    }

}