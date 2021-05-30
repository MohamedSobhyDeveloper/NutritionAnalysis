package com.openpark.nutritionanalysis.fragments

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.entities.ingredients.ModelIngredientsRequest
import com.openpark.nutritionanalysis.activity.MainActivity
import com.openpark.nutritionanalysis.R
import com.openpark.nutritionanalysis.activity.getDetails
import com.openpark.nutritionanalysis.activity.initializeNavController


fun FragmentIngredients.initalizeNavController(view: View) {

    (activity as MainActivity).initializeNavController(view)

}


fun FragmentIngredients.click() {
    binding.btnAddIngredients.setOnClickListener {
        if (binding.name.text.isNotEmpty() && binding.quantity.text.isNotEmpty()) {

            adapterIngredients.addItem(binding.quantity.text.toString() + " " + binding.name.text.toString() + " " + binding.unit.text.toString())
            binding.name.text.clear()
            binding.quantity.text.clear()
            binding.unit.text.clear()


            (activity as MainActivity).viewModel.updateIngredients(true)

        } else {
            binding.name.error = getString(R.string.enter_name)
            binding.quantity.error = getString(R.string.enter_quantity)

        }
    }


    binding.tvAnalysis.setOnClickListener {
        if (binding.edtTitle.text.isNotEmpty()) {
            val data = ModelIngredientsRequest(
                ingredientList!!,
                "",
                binding.edtTitle.text.toString(),
                ""
            )
            (activity as MainActivity).getDetails(
                data,
                getString(R.string.app_id),
                getString(R.string.app_key)
            )

        } else {
            binding.edtTitle.error = getString(R.string.enter_title)
        }
    }

}

fun FragmentIngredients.initalize() {

    (activity as MainActivity).viewModel.getSavedIngredients()

    (activity as MainActivity).viewModel.recreateLivedata.observe(requireActivity()) {
        ingredientList = it
        adapterIngredients.clear()
        adapterIngredients.addItems(ingredientList!!)
    }

    (activity as MainActivity).viewModel.setIngredientLivedata.observe(requireActivity()) {
        updateIngredient()
    }


}

fun FragmentIngredients.updateIngredient() {
    ingredientList = ArrayList()
    for (i in 0 until adapterIngredients.itemCount) {
        ingredientList!!.add(adapterIngredients.getItem(i))
    }

    (activity as MainActivity).viewModel.saveIngredients(ingredientList!!)

    if (ingredientList!!.size > 0) {
        binding.tvAnalysis.visibility = View.VISIBLE
    } else {
        binding.tvAnalysis.visibility = View.GONE

    }
}

fun FragmentIngredients.initRvIngredients() {
    binding.rvIngredients.layoutManager =
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    binding.rvIngredients.adapter = adapterIngredients

    adapterIngredients.setOnClickListener { clickedView, item, position ->

        (activity as MainActivity).viewModel.updateIngredients(true)
    }
}