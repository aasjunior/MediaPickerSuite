package com.aasjunior.mediapickersuite.config.preferences

import android.content.Context
import com.aasjunior.mediapickersuite.domain.constants.Language

class LanguagePreferences(context: Context){
    private val sharedPreferences = context.getSharedPreferences(
        "language_prefs",
        Context.MODE_PRIVATE
    )

    fun saveLanguage(language: Language){
        sharedPreferences.edit()
            .putString("language", language.name)
            .apply()
    }

    val language: Language?
        get() {
            val languageCode = sharedPreferences.getString("language", null)
            return Language.values().find { it.name == languageCode }
        }
}