package com.aasjunior.mediapickersuite.ui.components.contents

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.aasjunior.mediapickersuite.config.preferences.LanguageManager

private val LocalLanguageManager = compositionLocalOf<LanguageManager> {
    error("No LocalizedContext provided")
}
@Composable
fun LocalizedContent(
    content: @Composable () -> Unit
){
    val context = LocalContext.current
    val languageManager = remember {
        LanguageManager(context)
    }
    val currentLanguage = remember {
        mutableStateOf(languageManager.currentLanguage)
    }

    LaunchedEffect(Unit){
        currentLanguage.value = languageManager.currentLanguage
    }
    CompositionLocalProvider(LocalLanguageManager provides languageManager) {
        CompositionLocalProvider(
            LocalContext provides languageManager
                .updateResources(currentLanguage.value.locale)
        ) {
            content()
        }
    }
}