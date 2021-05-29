package com.openpark.nutritionanalysis.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.entities.ingredients.ModelIngredientsRequest
import com.openpark.nutritionanalysis.MainActivity
import com.openpark.nutritionanalysis.R
import com.openpark.nutritionanalysis.adapter.AdapterIngredients
import com.openpark.nutritionanalysis.databinding.FragmentIngredientsBinding
import com.openpark.nutritionanalysis.viewmodel.ViewModelNutrition
import com.spark.model.core.BaseFragment


class FragmentIngredients : BaseFragment<FragmentIngredientsBinding>() {
    val viewModel: ViewModelNutrition by lazy {
        ViewModelProvider(requireActivity()).get(
            ViewModelNutrition::class.java)
    }

    var ingredientList: MutableList<String>?=null

    private lateinit var navController: NavController
    val adapterIngredients by lazy { AdapterIngredients() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        initalize()

        click()

        initRvIngredients()


    }

    private fun click() {
        binding.btnAddIngredients.setOnClickListener {
            if (binding.edtIngredient.text.isNotEmpty()) {

                adapterIngredients.addItem(binding.edtIngredient.text.toString())
                binding.edtIngredient.text.clear()
                viewModel.updateIngredients(true)

            }
        }


        binding.tvAnalysis.setOnClickListener {
            if (binding.edtTitle.text.isNotEmpty()) {
                    val data=ModelIngredientsRequest(ingredientList!!,"",binding.edtTitle.text.toString(),"")
                (activity as MainActivity).getDetails(data,"9235320e","56054c3147c9bc4fbcfea4d05846eed3")
            }else{
                binding.edtTitle.error=getString(R.string.enter_title)
            }
        }

    }

    private fun initalize() {
        viewModel.setIngredientLivedata.observe(requireActivity()) {
            updateIngredient()
        }

        viewModel.openSummaryLivedata.observe(requireActivity()) {
            navController.navigate(R.id.action_fragmentIngredients_to_fragmentSummary)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun updateIngredient() {
        ingredientList=ArrayList()
        for (i in 0 until adapterIngredients.itemCount){
            ingredientList!!.add(adapterIngredients.getItem(i))
        }

        if (ingredientList!!.size>0){
            binding.tvAnalysis.visibility=View.VISIBLE
        }else{
            binding.tvAnalysis.visibility=View.GONE

        }
    }

    fun initRvIngredients() {
        binding.rvIngredients.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvIngredients.adapter = adapterIngredients

        adapterIngredients.setOnClickListener { clickedView, item, position ->

             viewModel.updateIngredients(true)
        }
    }

}