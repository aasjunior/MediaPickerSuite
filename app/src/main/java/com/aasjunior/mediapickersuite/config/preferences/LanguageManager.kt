package com.aasjunior.mediapickersuite.config.preferences

import android.content.Context
import android.content.res.Configuration
import com.aasjunior.mediapickersuite.domain.constants.Language
import java.util.Locale

class LanguageManager(private val context: Context) {
    private val languagePreferences = LanguagePreferences(context)

    var currentLanguage: Language
        get() = languagePreferences.language ?: Language.EN_US
        set(value) {
            languagePreferences.saveLanguage(value)
        }

    fun updateResources(locale: Locale): Context{
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)

    }
}