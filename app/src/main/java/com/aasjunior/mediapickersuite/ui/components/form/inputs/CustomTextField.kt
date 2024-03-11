package com.aasjunior.mediapickersuite.ui.components.form.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aasjunior.mediapickersuite.ui.viewmodel.validations.EmailState
import com.aasjunior.mediapickersuite.ui.viewmodel.visualtransformation.PhoneVisualTransformation

private val width = 264.dp
private val roundedCorner = 12.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    Column {
        OutlinedTextField(
            modifier = Modifier.width(width),
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            placeholder = {
                Text(label)
            },
            singleLine = singleLine,
            shape = RoundedCornerShape(roundedCorner),
            visualTransformation =
            if(isPassword) PasswordVisualTransformation()
            else visualTransformation
        )

        if (isError){
            Text(
                text = errorText ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun PhoneTextField(
    phone: String,
    onValueChange: (String) -> Unit
) {
    CustomOutlinedTextField(
        label = "Phone",
        value = phone,
        onValueChange = { newPhone ->
            if(newPhone.all { it.isDigit() } && newPhone.length <= 11) {
                onValueChange(newPhone)
            }
        },
        visualTransformation = PhoneVisualTransformation()
    )
}

@Composable
fun EmailTextField(
    email: String,
    onValueChange: (String) -> Unit
){
    val emailState = remember { EmailState() }
    emailState.setEmail(email)

    CustomOutlinedTextField(
        label = "Email",
        value = emailState.email,
        onValueChange = { newEmail ->
            emailState.setEmail(newEmail)
            onValueChange(newEmail)
        },
        isError = emailState.hasTriedToEnterEmail && emailState.error != null,
        errorText = emailState.error
    )
}

@Preview
@Composable
private fun PhoneTextFieldPreview(){
    var phone = "98765432109"

    PhoneTextField(
        phone = phone,
        onValueChange = { newPhone -> phone = newPhone }
    )
}