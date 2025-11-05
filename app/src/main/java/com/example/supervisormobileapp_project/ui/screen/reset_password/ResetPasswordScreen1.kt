package com.example.supervisormobileapp_project.ui.screen.reset_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
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
fun ResetPasswordScreen1(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    onBackClick: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
    email: String
) {
    var newPassword by remember { mutableStateOf("") }
    var confPassword by remember { mutableStateOf("") }

    var openDialogSuccess by remember { mutableStateOf(false) }
    var openDialogError by remember { mutableStateOf(false) }
    var openDialogVerifyTextField by remember { mutableStateOf(false) }
    var openDialogVerifyConfPassword by remember { mutableStateOf(false) }

    val resetPasswordMessage by authViewModel.resetPasswordMessage.collectAsStateWithLifecycle()
    val resetPasswordIsSuccess by authViewModel.isSuccess.collectAsStateWithLifecycle()


    fun changePassword() {
        authViewModel.resetPassword(
            email = email,
            newPassword = newPassword
        )
    }

    fun verifyConfPassword() {
        if (newPassword != confPassword) {
            openDialogVerifyConfPassword = true
        } else {
            changePassword()
        }
    }

    fun verifyTextField() {
        if (
            newPassword.isBlank() || confPassword.isBlank()
        ) {
            openDialogVerifyTextField = true
        } else if (newPassword.length < 8 || confPassword.length < 8) {
            openDialogVerifyTextField = true
        } else {
            verifyConfPassword()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
//                navigationIcon = {
//                    IconButton(onClick = { onBackClick() }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Icon Back",
//                            tint = Color(0XFFA9A9A9)
//                        )
//                    }
//                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Lupa Password",
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp
                )
                Text(
                    "Masukkan & Konfirmasi Password Baru",
                    fontSize = 14.sp
                )
            }
            Column {
                Text(
                    "Password baru",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(10.dp))
                CustomOutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    placeholder = "Masukkan password baru Anda",
                    isPassword = true
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    "Konfirmasi Password Baru",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(10.dp))
                CustomOutlinedTextField(
                    onValueChange = { confPassword = it },
                    value = confPassword,
                    placeholder = "Konfirmasi password baru Anda",
                    isPassword = true
                )
            }

            CustomButton(
                onClick = {
                    verifyTextField()
                },
                text = "Perbarui",
                color = Color(0xff3F845F)
            )
        }
        when {
            openDialogSuccess -> {
                CustomDialog(
                    onDismissRequest = {
                        openDialogSuccess = false
                        authViewModel.logout()
                        onNavigateToLogin()
                    },
                    title = {
                        Text(
                            text = "Pembaruan Password Berhasil",
                            color = Color(0xff3F845F),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                    content = {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Icon CheckCircle",
                            tint = Color(0xff3F845F)
                        )
                        Text(
                            text = "Silahkan Login Kembali",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Color(0xff3F845F),
                            textAlign = TextAlign.Center
                        )
                    },
                    dismissButton = {
                        Text(
                            modifier = Modifier.padding(vertical = 20.dp),
                            text = "Tutup",
                            color = Color(0xff3F845F),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    }
                )
            }
            openDialogError -> {
                CustomDialog(
                    onDismissRequest = {
                        openDialogError = false
                    },
                    title = {
                        Text(
                            text = "Pembaruan Password Gagal",
                            color = Color(0xffE25C5C),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                    content = {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon CheckCircle",
                            tint = Color(0xffE25C5C)
                        )
                        Text(
                            text = resetPasswordMessage,
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
                    }
                )
            }
            openDialogVerifyTextField -> {
                CustomDialog(
                    onDismissRequest = {
                        openDialogVerifyTextField = false
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
                            text = "Panjang password minimal 8 karakter",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Color(0xffE25C5C),
                            textAlign = TextAlign.Center
                        )
                    },
                    dismissButton = {
                        Text(
                            modifier = Modifier.padding(vertical = 20.dp),
                            text = "Batalkan",
                            color = Color(0xffE25C5C),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                )
            }

            openDialogVerifyConfPassword -> {
                CustomDialog(
                    onDismissRequest = {
                        openDialogVerifyConfPassword = false
                    },
                    title = {
                        Text(
                            text = "Pengulangan password salah",
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
                            text = "Pengulangan password baru harus sama",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Color(0xffE25C5C),
                            textAlign = TextAlign.Center
                        )
                    },
                    dismissButton = {
                        Text(
                            modifier = Modifier.padding(vertical = 20.dp),
                            text = "Batalkan",
                            color = Color(0xffE25C5C),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                )
            }

        }
    }
    LaunchedEffect(resetPasswordMessage) {
//        Log.d("changePasswordMessage", changePasswordMessage)
//        changePasswordMessage.let {
//            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//        }
        if (resetPasswordIsSuccess) {
            openDialogSuccess = true
//            authViewModel.logout()
//            onNavigateToLogin()
        }
        if (resetPasswordMessage.contains("Gagal")) {
            openDialogError = true
        }
    }


}

@Preview
@Composable
private fun ResetPasswordScreen1Preview() {
    ResetPasswordScreen1(onBackClick = {}, onNavigateToLogin = {}, email = "")
}
