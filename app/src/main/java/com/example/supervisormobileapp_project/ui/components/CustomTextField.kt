package com.example.supervisormobileapp_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: ((String) -> Unit)? = null,
    label: String
) {

    TextField(
        value = value,
        textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium,),
        onValueChange = {
            if (onValueChange != null) {
                onValueChange(it)
            }
        },
        readOnly = onValueChange == null,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,   // background saat fokus
            unfocusedContainerColor = Color.White, // background saat tidak fokus
            disabledContainerColor = Color.White,  // background saat disabled
            focusedIndicatorColor = Color(0xFFB0B0B0),
            unfocusedIndicatorColor = Color(0xFFB0B0B0),
            cursorColor = Color.Black,
            focusedLabelColor = Color(0xFFB0B0B0),
            unfocusedLabelColor = Color(0xFFB0B0B0),
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    CustomTextField(
        value = text,
        onValueChange = { text = it },
        label = "masukkan teks"
    )
}