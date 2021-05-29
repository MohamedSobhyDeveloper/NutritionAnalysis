package com.openpark.nutritionanalysis.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.openpark.nutritionanalysis.R
import com.openpark.nutritionanalysis.databinding.FragmentSummaryBinding
import com.spark.model.core.BaseFragment


class FragmentSummary : BaseFragment<FragmentSummaryBinding>()  {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        binding.btnc.setOnClickListener {
            navController.navigate(R.id.action_fragmentSummary_to_fragmentIngredients)

        }




    }



}