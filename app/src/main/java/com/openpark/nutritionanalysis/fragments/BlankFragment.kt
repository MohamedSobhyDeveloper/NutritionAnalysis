package com.openpark.nutritionanalysis.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.openpark.nutritionanalysis.R
import com.openpark.nutritionanalysis.databinding.FragmentBlankBinding
import com.spark.model.core.BaseFragment


class BlankFragment : BaseFragment<FragmentBlankBinding>() {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        navController.navigate(R.id.action_blankFragment2_to_fragmentIngredients)

    }
}