package com.example.supervisormobileapp_project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.supervisormobileapp_project.ui.components.CustomDialog

@Composable
fun TestDialog(modifier: Modifier = Modifier) {
    var openDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = {
            openDialog = true
        }) {
            Text("show dialog")
        }
    }
    when {
        openDialog -> {
            CustomDialog(
                onDismissRequest = { openDialog = false },
                title = {
                    Text(
                        text = "Read NFC Tag",
                        color = Color(0xffE25C5C),
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                    )
                },
                content = {
                    Icon(
                        modifier = Modifier.size(67.dp),
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Icon Cancel",
                        tint = Color(0xffE25C5C)
                    )
                    Text(
                        text = "NFC Tag Tidak Terdeteksi",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color(0xffE25C5C)
                    )
                },
                dismissButton = {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 20.dp),
                        text = "Tutup",
                        color = Color(0xffE25C5C),
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                    )
                }
            )
        }
    }
}

