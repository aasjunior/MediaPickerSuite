package com.aasjunior.mediapickersuite.ui.components.form.inputs

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    visualTransformation: VisualTransformation = VisualTransformation.None
){
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

@Preview
@Composable
private fun PhoneTextFieldPreview(){
    var phone = "98765432109"

    PhoneTextField(
        phone = phone,
        onValueChange = { newPhone -> phone = newPhone }
    )
}