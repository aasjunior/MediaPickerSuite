package com.aasjunior.mediapickersuite.ui.viewmodel.validations

import android.util.Patterns.EMAIL_ADDRESS

class EmailState {
    var email: String = ""
        private set

    var error: String? = null
    var hasTriedToEnterEmail = false
        private set

    fun setEmail(newEmail: String){
        if (newEmail != email) {
            hasTriedToEnterEmail = true
        }
        email = newEmail
        error = if(isValid()) null else "Invalid email"
    }
    private fun isValid() : Boolean{
        return EMAIL_ADDRESS.matcher(email).matches()
    }
}

