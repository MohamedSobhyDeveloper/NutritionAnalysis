package com.openpark.nutritionanalysis.fragments

import android.os.Bundle
import android.view.View
import com.openpark.nutritionanalysis.adapter.AdapterIngredients
import com.openpark.nutritionanalysis.databinding.FragmentIngredientsBinding
import com.kt.core.base.BaseFragment


class FragmentIngredients : BaseFragment<FragmentIngredientsBinding>() {


    var ingredientList: ArrayList<String>? = null

    val adapterIngredients by lazy { AdapterIngredients() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initalizeNavController(view)

        initRvIngredients()

        initalize()

        click()


    }





}