package com.kt.core.utlities

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.kt.usecase.applicationLiveData

import java.security.AccessController.getContext
import java.util.*


object LocaleHelperN {

  private const val SELECTED_LANGUAGE = "AppliedLanguage"
  private val pref by lazy { getDefaultSharedPreferences( applicationLiveData.value) }
  private lateinit var context: Context

  var locale: String
    get() = pref.getString(SELECTED_LANGUAGE, Locale.getDefault().language) as String
    set(language) {
      pref.edit().putString(SELECTED_LANGUAGE, language).apply()
      updateResources(locale)
    }

  fun onAttach(context: Context): Context {
    this.context = context
    return updateResources(locale)
  }

  private fun updateResources(language: String): Context {
    val locale = Locale(language, if (language == "ar") "EG" else "US")
    Locale.setDefault(locale)

    val configuration = context.resources.configuration
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)

    return context.createConfigurationContext(configuration)
  }
}