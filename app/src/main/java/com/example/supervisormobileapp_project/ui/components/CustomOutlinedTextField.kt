package com.example.supervisormobileapp_project.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean,
) {
    var isVisible by remember { mutableStateOf(!isPassword) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done // ubah tombol enter jadi "Done"
        ),
        placeholder = {
            Text(
                placeholder,
                fontSize = 16.sp,
                color = Color(0XFFA9A9A9)
            )
        },
        trailingIcon = {
            if (isPassword)
                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(
                        if (isVisible)
                            Icons.Outlined.Visibility
                        else Icons.Outlined.VisibilityOff,
                        contentDescription = "Visibility Icon",
                        tint = Color(0XFF3F845F)
                    )
                }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF3F845F),   // border saat fokus
            unfocusedBorderColor = Color(0xFF3F845F), // border saat tidak fokus
            cursorColor = Color(0xFF3F845F),          // warna kursor
            focusedLabelColor = Color(0xFF3F845F)     // kalau pakai label
        ),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomOutlinedTextFieldPreview() {
    var value by remember { mutableStateOf("") }
    CustomOutlinedTextField(
        value = value,
        onValueChange = { value = it },
        placeholder = "Masukkan email anda",
        isPassword = true
    )

}