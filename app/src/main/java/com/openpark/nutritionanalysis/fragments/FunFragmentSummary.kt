package com.openpark.nutritionanalysis.fragments

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.entities.ingredientsdetails.ModelDetailsIngredient
import com.openpark.nutritionanalysis.activity.MainActivity
import com.openpark.nutritionanalysis.R




 fun FragmentSummary.click() {
    binding.tvShowTotal.setOnClickListener {
        navController.navigate(R.id.action_fragmentSummary_to_fragmentTotalNutrition)
    }
}

@SuppressLint("SetTextI18n")
 fun FragmentSummary.initialize(savedData: ModelDetailsIngredient) {


    binding.tvTotal.text = getString(R.string.total_weight) + savedData.totalWeight.toString()
    binding.tvCalories.text = getString(R.string.calories) + savedData.calories.toString()

    (activity as MainActivity).viewModel.getSavedIngredients()

    (activity as MainActivity).viewModel.recreateLivedata.observe(requireActivity()) {
        adapterIngredients.clear()
        setUpIngredients(it)
    }

}

 fun FragmentSummary.setUpIngredients(it: ArrayList<String>?) {
    binding.rvIngredients.layoutManager =
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    binding.rvIngredients.adapter = adapterIngredients
    adapterIngredients.addItems(it!!)


}
