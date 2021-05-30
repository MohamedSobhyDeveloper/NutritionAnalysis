package com.openpark.nutritionanalysis.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.entities.ingredients.ModelIngredientsRequest
import com.openpark.nutritionanalysis.R
import com.openpark.nutritionanalysis.adapter.AdapterIngredients
import com.openpark.nutritionanalysis.databinding.FragmentIngredientsBinding
import com.openpark.nutritionanalysis.viewmodel.ViewModelNutrition
import com.kt.core.base.BaseFragment
import com.openpark.nutritionanalysis.MainActivity


class FragmentIngredients : BaseFragment<FragmentIngredientsBinding>() {


    var ingredientList: ArrayList<String>?=null

    val adapterIngredients by lazy { AdapterIngredients() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         initalizeNavController(view)

        initRvIngredients()

        initalize()

        click()



    }

    private fun initalizeNavController(view: View) {
        (activity as MainActivity).initializeNavController(view)

    }


    private fun click() {
        binding.btnAddIngredients.setOnClickListener {
            if (binding.name.text.isNotEmpty()&&binding.quantity.text.isNotEmpty()) {

                adapterIngredients.addItem(binding.quantity.text.toString()+" "+binding.name.text.toString()+" "+binding.unit.text.toString())
                binding.name.text.clear()
                (activity as MainActivity).viewModel.updateIngredients(true)

            }else{
                binding.name.error="Enter name"
                binding.quantity.error="Enter quantity"

            }
        }


        binding.tvAnalysis.setOnClickListener {
            if (binding.edtTitle.text.isNotEmpty()) {
                 val data= ModelIngredientsRequest(ingredientList!!,"",binding.edtTitle.text.toString(),"")
                (activity as MainActivity).getDetails(data,"9235320e","56054c3147c9bc4fbcfea4d05846eed3")
//
//                viewModel.openSummary(true)
//                navController.navigate(R.id.action_fragmentIngredients_to_fragmentSummary)


            }else{
                binding.edtTitle.error=getString(R.string.enter_title)
            }
        }

    }

    private fun initalize() {

        (activity as MainActivity).viewModel.getSavedIngredients()

        (activity as MainActivity).viewModel.recreateLivedata.observe(requireActivity()){
            ingredientList=it
            adapterIngredients.clear()
            adapterIngredients.addItems(ingredientList!!)
        }

        (activity as MainActivity).viewModel.setIngredientLivedata.observe(requireActivity()) {
            updateIngredient()
        }


    }

    private fun updateIngredient() {
        ingredientList=ArrayList()
        for (i in 0 until adapterIngredients.itemCount){
            ingredientList!!.add(adapterIngredients.getItem(i))
        }

        (activity as MainActivity).viewModel.saveIngredients(ingredientList!!)

        if (ingredientList!!.size>0){
            binding.tvAnalysis.visibility=View.VISIBLE
        }else{
            binding.tvAnalysis.visibility=View.GONE

        }
    }

    fun initRvIngredients() {
        binding.rvIngredients.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvIngredients.adapter = adapterIngredients

        adapterIngredients.setOnClickListener { clickedView, item, position ->

            (activity as MainActivity).viewModel.updateIngredients(true)
        }
    }

}