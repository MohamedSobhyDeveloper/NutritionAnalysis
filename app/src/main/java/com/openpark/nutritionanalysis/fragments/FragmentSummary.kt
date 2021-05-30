package com.openpark.nutritionanalysis.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.entities.ingredientsdetails.ModelDetailsIngredient
import com.openpark.nutritionanalysis.databinding.FragmentSummaryBinding
import com.kt.core.base.BaseFragment
import com.openpark.nutritionanalysis.MainActivity
import com.openpark.nutritionanalysis.R
import com.openpark.nutritionanalysis.adapter.AdapterIngredientsSummary


class FragmentSummary : BaseFragment<FragmentSummaryBinding>()  {
    private lateinit var navController: NavController
    val adapterIngredients by lazy { AdapterIngredientsSummary() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initialize((activity as MainActivity).viewModel.getSavedData())

        click()




    }

    private fun click() {
        binding.tvShowTotal.setOnClickListener {
            navController.navigate(R.id.action_fragmentSummary_to_fragmentTotalNutrition)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initialize(savedData: ModelDetailsIngredient) {
        binding.tvTotal.text="Total Weight:- "+savedData.totalWeight.toString()
        binding.tvCalories.text="Calories :- "+savedData.calories.toString()

        (activity as MainActivity).viewModel.getSavedIngredients()

        (activity as MainActivity).viewModel.recreateLivedata.observe(requireActivity()){
            adapterIngredients.clear()
            setUpIngredients(it)
        }

    }

    private fun setUpIngredients(it: ArrayList<String>?) {
        binding.rvIngredients.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvIngredients.adapter = adapterIngredients
        adapterIngredients.addItems(it!!)


    }


}