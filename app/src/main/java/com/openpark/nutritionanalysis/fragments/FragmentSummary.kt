package com.openpark.nutritionanalysis.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.openpark.nutritionanalysis.databinding.FragmentSummaryBinding
import com.kt.core.base.BaseFragment
import com.openpark.nutritionanalysis.activity.MainActivity
import com.openpark.nutritionanalysis.adapter.AdapterIngredientsSummary


class FragmentSummary : BaseFragment<FragmentSummaryBinding>() {
    lateinit var navController: NavController
    val adapterIngredients by lazy { AdapterIngredientsSummary() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initialize((activity as MainActivity).viewModel.getSavedData())

        click()


    }



}