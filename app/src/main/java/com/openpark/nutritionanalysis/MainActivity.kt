package com.openpark.nutritionanalysis

import android.os.Bundle
import androidx.activity.viewModels
import com.kt.core.base.BaseActivity
import com.openpark.nutritionanalysis.databinding.ActivityMainBinding
import com.openpark.nutritionanalysis.viewmodel.ViewModelNutrition

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val viewModel by viewModels<ViewModelNutrition>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideToolbar()

    }


}