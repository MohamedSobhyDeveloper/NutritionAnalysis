package com.openpark.nutritionanalysis.fragments

import android.os.Bundle
import android.view.View
import com.openpark.nutritionanalysis.databinding.FragmentTotalNutritionBinding
import com.kt.core.base.BaseFragment
import com.openpark.nutritionanalysis.activity.MainActivity


class FragmentTotalNutrition : BaseFragment<FragmentTotalNutritionBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize((activity as MainActivity).viewModel.getSavedData())

    }




}