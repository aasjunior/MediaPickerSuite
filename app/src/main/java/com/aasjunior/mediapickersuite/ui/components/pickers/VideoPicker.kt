package com.aasjunior.mediapickersuite.ui.components.pickers

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VideoPicker(){
    val result = remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ){
        result.value = it
    }

    Column {
        Button(onClick = {
            launcher.launch(
                PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly
                )
            )
        }){
            Text(text = "Select Video")
        }

        result.value?.let { image ->
            Text(text = "Video Path: ${image.path.toString()}")
        }
    }
}

@Preview
@Composable
private fun VideoPickerPreview(){
    VideoPicker()
}