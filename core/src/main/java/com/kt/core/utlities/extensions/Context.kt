package com.kt.core.utlities.extensions

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager




fun Context.hideKeyboard(view: View?) = view?.let {
  val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
  if (imm.isActive) {
    imm.hideSoftInputFromWindow(it.windowToken, 0)
  }
}

