package com.openpark.nutritionanalysis.adapter

import android.view.View
import com.kt.core.base.BaseAdapter
import com.openpark.nutritionanalysis.databinding.RvIngredientsItemBinding

class AdapterIngredientsSummary : BaseAdapter<RvIngredientsItemBinding, String>() {
    override fun setContent(binding: RvIngredientsItemBinding, item: String, position: Int) {


        binding.btnclear.visibility = View.GONE

        binding.tvTitle.text = item

    }
}