package com.aasjunior.mediapickersuite.ui.components.form.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    roundedCornerShape: Boolean = false,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    Column {
        OutlinedTextField(
            modifier = Modifier
                .width(width),
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
            shape =
                if (roundedCornerShape)
                    RoundedCornerShape(percent = 50)
                else
                    RoundedCornerShape(roundedCorner),
            visualTransformation =
                if(isPassword && !passwordVisibility) PasswordVisualTransformation()
                else visualTransformation,
            trailingIcon = {
                if(isPassword){
                    IconButton(
                        onClick = {
                            passwordVisibility = !passwordVisibility
                        }
                    ) {
                      Icon(
                          imageVector =
                                if(passwordVisibility) Icons.Filled.FavoriteBorder
                                else Icons.Filled.Favorite,
                          contentDescription = "Toggle password visibility"
                      )
                    }
                }
            }
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
    onValueChange: (String) -> Unit,
    roundedCornerShape: Boolean = false
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
        roundedCornerShape = roundedCornerShape,
        isError = emailState.hasTriedToEnterEmail && emailState.error != null,
        errorText = emailState.error
    )
}

@Composable
fun PasswordTextField(
    password: String,
    onValueChange: (String) -> Unit,
    roundedCornerShape: Boolean = true
){
    CustomOutlinedTextField(
        label = "Password",
        value = password,
        onValueChange = onValueChange,
        isPassword = true,
        roundedCornerShape = roundedCornerShape
    )
}

@Composable
fun ConfirmPassword(){
    var password by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    var isPasswordConfirmed by remember {
        mutableStateOf(true)
    }

    Column {
        PasswordTextField(
            password = password,
            onValueChange = { newPassword ->
                password = newPassword
                if(confirmPassword.isNotEmpty())
                    isPasswordConfirmed = password == confirmPassword
            }
        )

        PasswordTextField(
            password = confirmPassword,
            onValueChange = { newPassword ->
                confirmPassword = newPassword
                isPasswordConfirmed = password == confirmPassword
            }
        )

        if(!isPasswordConfirmed && confirmPassword.isNotEmpty())
            Text("Passwords do not match")
    }
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