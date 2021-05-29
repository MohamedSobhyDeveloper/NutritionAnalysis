package com.openpark.nutritionanalysis.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.entities.ingredients.ModelIngredientsRequest
import com.usecase.network.RestClient
import com.usecase.network.loadDataGeneric


class ViewModelNutrition :ViewModel() {
    val setIngredientLivedata= MutableLiveData<Boolean>()
    val openSummaryLivedata= MutableLiveData<Boolean>()

    fun apiIngredientDetails(data:ModelIngredientsRequest,appId:String,appKey:String) = loadDataGeneric{

        RestClient.apiService.getNutritionDetails(data,appId,appKey)
    }

    fun  updateIngredients(update:Boolean){
        setIngredientLivedata.value=update
    }



    fun  openSummary(update:Boolean){
        openSummaryLivedata.value=update
    }

}