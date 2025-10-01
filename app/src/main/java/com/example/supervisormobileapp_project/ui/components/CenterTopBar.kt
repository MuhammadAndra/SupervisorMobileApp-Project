package com.example.supervisormobileapp_project.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopBar(
    modifier: Modifier = Modifier,
    title: String,
    color: Color? = null,
    onBackClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick,

                ){
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back button",
                        tint = Color.White
                    )
                }
            }
        },
        title = {
            Text(
                title,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
                color = Color.White
            )
        },
        colors =
            if (color != null) {
                TopAppBarDefaults.centerAlignedTopAppBarColors(color)
            } else {
                TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent)
            }
    )
}