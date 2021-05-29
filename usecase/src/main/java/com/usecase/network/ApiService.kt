package com.usecase.network


import com.entities.ingredients.ModelIngredientsRequest
import com.entities.ingredientsdetails.ModelDetailsIngredient
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @POST("nutrition-details")
    suspend fun getNutritionDetails(@Body body: ModelIngredientsRequest, @Query("app_id")  appId:String, @Query("app_key")  appKey:String): Response<ModelDetailsIngredient>


}