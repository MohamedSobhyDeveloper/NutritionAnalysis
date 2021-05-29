package com.openpark.nutritionanalysis

import android.os.Bundle
import androidx.activity.viewModels
import com.entities.ingredients.ModelIngredientsRequest
import com.entities.ingredientsdetails.ModelDetailsIngredient
import com.kt.core.base.BaseActivity
import com.openpark.nutritionanalysis.databinding.ActivityMainBinding
import com.openpark.nutritionanalysis.viewmodel.ViewModelNutrition

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val viewModel by viewModels<ViewModelNutrition>()
    var dataModel:ModelDetailsIngredient?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideToolbar()



    }

     fun getDetails(data: ModelIngredientsRequest, appId: String, appKey: String) {
        viewModel.apiIngredientDetails(data,appId,appKey).observe(this, {
            if (callApi(it) { getDetails(data,appId,appKey) }) {
                it.data?.let { it1 ->
                    dataModel=it1
                    viewModel.openSummary(true)

                }
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


}