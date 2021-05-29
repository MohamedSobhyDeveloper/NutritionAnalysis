package com.openpark.nutritionanalysis.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.entities.ingredients.ModelIngredientsRequest
import com.entities.ingredientsdetails.ModelDetailsIngredient
import com.usecase.network.RestClient
import com.usecase.network.loadDataGeneric


class ViewModelNutrition :ViewModel() {
    val setIngredientLivedata= MutableLiveData<Boolean>()
    val openSummaryLivedata= MutableLiveData<Boolean>()
    val recreateLivedata= MutableLiveData<ArrayList<String>>()
    var list:ArrayList<String>?=null

    fun apiIngredientDetails(data:ModelIngredientsRequest,appId:String,appKey:String) = loadDataGeneric{

        RestClient.apiService.getNutritionDetails(data,appId,appKey)
    }

    fun  updateIngredients(update:Boolean){
        setIngredientLivedata.value=update
    }


    fun  saveIngredients(ingredientsList:ArrayList<String>){
         list=ingredientsList
    }


    fun  getSavedIngredients(){
        if (list!=null){
            recreateLivedata.value=list
        }

    }


    fun  savedData(it1: ModelDetailsIngredient): ModelDetailsIngredient {
         return it1
    }




    fun  openSummary(update:Boolean){
        openSummaryLivedata.value=update
    }

}