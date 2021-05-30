package com.openpark.nutritionanalysis.activity


import android.view.View
import androidx.navigation.Navigation
import com.entities.ingredients.ModelIngredientsRequest
import com.openpark.nutritionanalysis.R


fun MainActivity.getDetails(data: ModelIngredientsRequest, appId: String, appKey: String) {
    viewModel.apiIngredientDetails(data,appId,appKey).observe(this, {
        if (callApi(it) { getDetails(data,appId,appKey) }) {
            it.data?.let { it1 ->
                dataModel=it1
                viewModel.savedData(it1)
                navController.navigate(R.id.action_fragmentIngredients_to_fragmentSummary)

            }
        }

    })
}


fun MainActivity.initializeNavController(view: View){
    navController = Navigation.findNavController(view)

}

