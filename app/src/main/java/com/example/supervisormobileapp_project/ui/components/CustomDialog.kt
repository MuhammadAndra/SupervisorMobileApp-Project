package com.example.supervisormobileapp_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    onConfirmation: (() -> Unit)? = null,
    onDismissRequest: () -> Unit,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    confirmButton: @Composable (() -> Unit)? = null
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(color = Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    title()
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFFC0C0C0)
                )
                Column(
                    modifier = Modifier
                        .padding(28.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    content()
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFFC0C0C0)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (onConfirmation != null && confirmButton != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .clickable {
                                    onConfirmation()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            confirmButton()
                        }
                        VerticalDivider(
                            modifier = Modifier.height(66.dp),
                            thickness = 1.dp,
                            color = Color(0xFFC0C0C0)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clickable {
                                onDismissRequest()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        dismissButton()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CustomDialogPreview() {
    CustomDialog(
        onConfirmation = { },
        onDismissRequest = { },
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
                modifier = Modifier.padding(vertical = 20.dp),
                text = "Tutup",
                color = Color(0xffE25C5C),
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
            )
        }
    )
}