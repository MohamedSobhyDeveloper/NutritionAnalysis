package com.openpark.nutritionanalysis

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.entities.ingredients.ModelIngredientsRequest
import com.entities.ingredientsdetails.ModelDetailsIngredient
import com.kt.core.base.BaseActivity
import com.openpark.nutritionanalysis.databinding.ActivityMainBinding
import com.openpark.nutritionanalysis.viewmodel.ViewModelNutrition

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val viewModel by viewModels<ViewModelNutrition>()
    var dataModel:ModelDetailsIngredient?=null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

     fun getDetails(data: ModelIngredientsRequest, appId: String, appKey: String) {
        viewModel.apiIngredientDetails(data,appId,appKey).observe(this, {
            if (callApi(it) { getDetails(data,appId,appKey) }) {
                it.data?.let { it1 ->
                    dataModel=it1
                    viewModel.savedData(it1)
                    navController.navigate(R.id.action_fragmentIngredients_to_fragmentSummary)

//                    viewModel.openSummary(true)

                }
            }

        })
    }

    fun initializeNavController(view: View){
        navController = Navigation.findNavController(view)

    }



}