package com.openpark.nutritionanalysis.adapter

import com.kt.core.base.BaseAdapter
import com.kt.core.utlities.extensions.clickWithDebounce
import com.openpark.nutritionanalysis.databinding.RvIngredientsItemBinding

class AdapterIngredients : BaseAdapter<RvIngredientsItemBinding, String>() {
    override fun setContent(binding: RvIngredientsItemBinding, item: String, position: Int) {


        binding.btnclear.clickWithDebounce {
            removeItem(position)
            onViewClicked(binding.root, item, position)

        }

        binding.tvTitle.text = item

    }
}