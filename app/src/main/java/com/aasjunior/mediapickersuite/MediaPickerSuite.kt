package com.aasjunior.mediapickersuite

import android.app.Application
import com.aasjunior.mediapickersuite.config.Injector

class MediaPickerSuite : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.initialize(this)
    }
}