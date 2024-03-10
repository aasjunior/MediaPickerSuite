package com.aasjunior.mediapickersuite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.aasjunior.mediapickersuite.ui.components.form.inputs.PhoneTextField
import com.aasjunior.mediapickersuite.ui.components.pickers.DocumentPicker
import com.aasjunior.mediapickersuite.ui.components.pickers.ImagePicker
import com.aasjunior.mediapickersuite.ui.components.pickers.VideoPicker
import com.aasjunior.mediapickersuite.ui.theme.MediaPickerSuiteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaPickerSuiteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        ImagePicker()
                        VideoPicker()
                        DocumentPicker()

                        var phone by rememberSaveable { mutableStateOf("") }

                        PhoneTextField(
                            phone = phone,
                            onValueChange = { newPhone ->
                                phone = newPhone
                            }
                        )
                    }
                    
                }
            }
        }
    }
}