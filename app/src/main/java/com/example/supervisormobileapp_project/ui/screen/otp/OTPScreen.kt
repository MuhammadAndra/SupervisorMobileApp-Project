package com.example.supervisormobileapp_project.ui.screen.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supervisormobileapp_project.ui.components.CustomButton
import com.example.supervisormobileapp_project.ui.components.CustomOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPScreen(
    modifier: Modifier = Modifier,
    onNavigateToChangePassword: () -> Unit,
    onBackClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }

    fun onSendEmail() {}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Icon Back",
                            tint = Color(0XFFA9A9A9)
                        )
                    }
                }
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Kode OTP",
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp
                )
                Text(
                    "Masukkan E-mail anda untuk menerima kode OTP",
                    fontSize = 14.sp
                )
            }
            Column {
                Text("Email", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(10.dp))
                CustomOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Masukkan email anda",
                    isPassword = false
                )
                Spacer(Modifier.height(16.dp))
                CustomButton(
                    onClick = { onSendEmail() },
                    text = "Kirim",
                    color = Color(0xff3F845F)
                )
            }

            Column {
                Text(
                    "Kode OTP",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(10.dp))
                CustomOutlinedTextField(
                    value = otp,
                    onValueChange = { otp = it },
                    placeholder = "Masukkan kode OTP",
                    isPassword = false
                )
                Spacer(Modifier.height(16.dp))
                CustomButton(
                    onClick = { onNavigateToChangePassword() },
                    text = "Kirim",
                    color = Color(0xff3F845F)
                )
            }
        }
    }
}

@Preview
@Composable
private fun OTPScreenPreview() {
    OTPScreen(onNavigateToChangePassword = {}, onBackClick = {})
}