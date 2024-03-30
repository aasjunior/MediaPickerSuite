package com.aasjunior.mediapickersuite.ui.components.settings

import android.app.ActivityManager
import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aasjunior.mediapickersuite.R
import com.aasjunior.mediapickersuite.config.Injector
import com.aasjunior.mediapickersuite.domain.constants.Language
import com.aasjunior.mediapickersuite.ui.viewmodel.LoginViewModel

@Composable
fun LanguageSelector(loginViewModel: LoginViewModel){
    val context = LocalContext.current
    val languageManager = remember { Injector.provideLanguageManager() }
    val currentLanguage = remember { mutableStateOf(languageManager.currentLanguage) }
    val expanded = remember { mutableStateOf(false) }

    var isLoggedIn = loginViewModel.isLoggedIn()
        .collectAsState(initial = false)


    Button(onClick = { expanded.value = true }) {
        Text(text = stringResource(id = R.string.change_language))
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false }
    ) {
        Language.values().forEach { language ->
            DropdownMenuItem(
                text = {
                       Text(text = language.name)
                },
                onClick = {
                    if(language == currentLanguage.value)
                        return@DropdownMenuItem

                    currentLanguage.value = language
                    languageManager.currentLanguage = language
                    expanded.value = false

                    restartActivity(context, isLoggedIn.value)
                }
            )
        }
    }
}

private fun restartActivity(context: Context, isLoggedIn: Boolean){
    val activityManager = ContextCompat
        .getSystemService(context, ActivityManager::class.java)

    val appTasks = activityManager?.appTasks

    val launchIntent = context.packageManager
        .getLaunchIntentForPackage(context.packageName)

    launchIntent?.putExtra("isLoggedIn", isLoggedIn)
    appTasks?.get(0)?.finishAndRemoveTask()
    appTasks?.get(0)?.startActivity(context, launchIntent, null)
}