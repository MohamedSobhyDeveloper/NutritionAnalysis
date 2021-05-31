package com.openpark.nutritionanalysis.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.entities.ingredientsdetails.ModelDetailsIngredient
import com.kt.core.base.BaseActivity
import com.openpark.nutritionanalysis.databinding.ActivityMainBinding
import com.openpark.nutritionanalysis.viewmodel.ViewModelNutrition

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val viewModel by viewModels<ViewModelNutrition>()
    var dataModel: ModelDetailsIngredient? = null
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}