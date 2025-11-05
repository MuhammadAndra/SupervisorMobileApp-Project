package com.example.supervisormobileapp_project.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.supervisormobileapp_project.ui.AuthViewModel
import com.example.supervisormobileapp_project.ui.components.CustomButton
import com.example.supervisormobileapp_project.ui.components.CustomOutlinedTextField
import kotlin.text.contains
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToOTP: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    val loginMessage by authViewModel.loginMessage.collectAsStateWithLifecycle()


    fun login() {
        if (email.isNotBlank() && password.isNotBlank()) {
            authViewModel.login(email, password)
        } else {
            Toast.makeText(
                context,
                "Email dan password harus diisi",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Masuk", fontSize = 22.sp, fontWeight = FontWeight.Medium)
            Text("Masuk ke akun anda untuk melanjutkan", fontSize = 14.sp)
        }
        Spacer(Modifier.height(70.dp))
        Column {
            Text("Email", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(10.dp))
            CustomOutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Masukkan email anda",
                isPassword = false
            )
        }
        Spacer(Modifier.height(10.dp))
        Column {
            Text("Password", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(10.dp))
            CustomOutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Masukkan password anda",
                isPassword = true
            )
        }
        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier
                    .clickable { onNavigateToOTP() }
                    .padding(all = 5.dp),
                text = "Ubah Password",
                fontSize = 14.sp,
                style = TextStyle(
                    color = Color(0XFF3F845F),
                    textDecoration = TextDecoration.Underline
                )
            )
        }
        Spacer(Modifier.height(20.dp))
        CustomButton(
            onClick = { login() },
            text = "Masuk",
            color = Color(0xff3F845F)
        )
        LaunchedEffect(loginMessage) {
            if(loginMessage!=""){
                Toast.makeText(context, loginMessage, Toast.LENGTH_SHORT).show()
            }
//            loginMessage.let {
//                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//            }
            if (loginMessage.contains("berhasil", ignoreCase = true)) {
                onNavigateToHome()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(onNavigateToOTP = {}, onNavigateToHome = {})
}