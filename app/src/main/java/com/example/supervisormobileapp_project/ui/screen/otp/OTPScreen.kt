package com.example.supervisormobileapp_project.ui.screen.otp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.supervisormobileapp_project.ui.AuthViewModel
import com.example.supervisormobileapp_project.ui.components.CustomButton
import com.example.supervisormobileapp_project.ui.components.CustomDialog
import com.example.supervisormobileapp_project.ui.components.CustomOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPScreen(
    modifier: Modifier = Modifier,
    onNavigateToResetPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    val forgotPasswordMessage by authViewModel.requestOtpMessage.collectAsStateWithLifecycle()
    val verifyOtpMessage by authViewModel.verifyOtpMessage.collectAsStateWithLifecycle()
    val isVerified by authViewModel.isOtpVerified.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var openDialogVerifyEmail by remember { mutableStateOf(false) }
    var openDialogVerifyOTP by remember { mutableStateOf(false) }
    var lockEmail by remember { mutableStateOf(false) }


    fun requestOtp() {
        authViewModel.requestOtp(email = email)
    }

    fun checkEmail() {
        if (
            email.isBlank() || !email.contains("@")
        ) {
            openDialogVerifyEmail = true
        } else {
            requestOtp()
        }
    }

    fun verifyOtp() {
        lockEmail = true
        authViewModel.verifyOtp(email = email, otp = otp)
    }

    fun checkOtp() {
        if (otp.isBlank() || email.isBlank() || !email.contains("@")) {
            openDialogVerifyOTP
        } else {
            verifyOtp()
        }
    }
    LaunchedEffect(forgotPasswordMessage) {
        if (forgotPasswordMessage.contains("dikirim") || forgotPasswordMessage.contains(
                "gagal"
            )
        ) {
            Toast.makeText(
                context, forgotPasswordMessage, Toast.LENGTH_SHORT
            ).show()
        }
    }
    LaunchedEffect(verifyOtpMessage) {
        if (verifyOtpMessage.contains("berhasil")) {
            Toast.makeText(
                context, verifyOtpMessage, Toast.LENGTH_SHORT
            ).show()
            onNavigateToResetPassword(email)
        } else if (verifyOtpMessage.contains("gagal")) {
            lockEmail = false
            Toast.makeText(
                context, verifyOtpMessage, Toast.LENGTH_SHORT
            ).show()
        }else if (verifyOtpMessage.contains("berhasil") || verifyOtpMessage.contains("gagal")
        ) {
            Toast.makeText(
                context, verifyOtpMessage, Toast.LENGTH_SHORT
            ).show()
        }
    }


//    fun onSendEmail() {
//        if (email.isBlank()) {
//            Toast.makeText(
//                context,
//                "Email tidak boleh kosong!",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            authViewModel.requestOtp(email)
//        }
//    }

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
                    readOnly = lockEmail,
                    onValueChange = { email = it },
                    placeholder = "Masukkan email anda",
                    isPassword = false
                )
                Spacer(Modifier.height(16.dp))
                CustomButton(
                    onClick = { checkEmail() },
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
                    onClick = { checkOtp() },
                    text = "Kirim",
                    color = Color(0xff3F845F)
                )
            }
        }
    }
    when {
        openDialogVerifyEmail -> {
            CustomDialog(
                onDismissRequest = {
                    openDialogVerifyEmail = false
                },
                title = {
                    Text(
                        text = "Lengkapi Kolom",
                        color = Color(0xffE25C5C),
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                    )
                },
                content = {
                    Icon(
                        modifier = Modifier.size(80.dp),
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Icon Cancel",
                        tint = Color(0xffE25C5C)
                    )
                    Text(
                        text = "Tulis Email dengan benar",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color(0xffE25C5C),
                        textAlign = TextAlign.Center
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
                },
            )
        }

        openDialogVerifyOTP -> {
            CustomDialog(
                onDismissRequest = {
                    openDialogVerifyEmail = false
                },
                title = {
                    Text(
                        text = "Lengkapi Kolom",
                        color = Color(0xffE25C5C),
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                    )
                },
                content = {
                    Icon(
                        modifier = Modifier.size(80.dp),
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Icon Cancel",
                        tint = Color(0xffE25C5C)
                    )
                    Text(
                        text = "Email dan Kode OTP Tidak Boleh Kosong",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color(0xffE25C5C),
                        textAlign = TextAlign.Center
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
                },
            )
        }
    }

}

@Preview
@Composable
private fun OTPScreenPreview() {
    OTPScreen(onNavigateToResetPassword = {}, onBackClick = {})
}