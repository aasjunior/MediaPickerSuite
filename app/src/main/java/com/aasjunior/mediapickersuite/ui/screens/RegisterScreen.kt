package com.aasjunior.mediapickersuite.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aasjunior.mediapickersuite.domain.model.user.UserRole
import com.aasjunior.mediapickersuite.ui.components.contents.appbar.AppBar
import com.aasjunior.mediapickersuite.ui.components.form.inputs.ConfirmPassword
import com.aasjunior.mediapickersuite.ui.components.form.inputs.CustomOutlinedTextField
import com.aasjunior.mediapickersuite.ui.components.form.inputs.EmailTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(drawerState: DrawerState) {
    Scaffold(
        topBar = { AppBar(drawerState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var name by rememberSaveable { mutableStateOf("") }
            var username by rememberSaveable { mutableStateOf("") }
            var email by rememberSaveable { mutableStateOf("") }
            val roles = UserRole.values().map { it.name }
            var role by rememberSaveable { mutableStateOf(roles[0]) }
            var expanded by remember { mutableStateOf(false) }

            CustomOutlinedTextField(
                label = "Name",
                value = name,
                onValueChange = { newName -> name = newName}
            )

            CustomOutlinedTextField(
                label = "Username",
                value = username,
                onValueChange = { newUsername -> username = newUsername}
            )

            EmailTextField(
                email = email,
                onValueChange = { newEmail -> email = newEmail },
                roundedCornerShape = true
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    label = { Text("Role") },
                    value = role?.let { it.toString() } ?: "",
                    readOnly = true,
                    onValueChange = {},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults
                            .TrailingIcon(expanded = expanded)
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    roles.forEach { selectionRole ->
                        DropdownMenuItem(
                            text = { Text(selectionRole) },
                            onClick = {
                                role = selectionRole
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            ConfirmPassword()

        }
    }
}