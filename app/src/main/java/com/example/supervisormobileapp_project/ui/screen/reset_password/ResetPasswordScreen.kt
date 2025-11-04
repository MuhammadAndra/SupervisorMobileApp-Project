package com.example.supervisormobileapp_project.ui.screen.reset_password

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun ResetPasswordScreen(modifier: Modifier = Modifier, navCtrl: NavController, email: String) {

    var newPass by remember { mutableStateOf("") }
    var repeatNewPass by remember { mutableStateOf("") }
    var newPassIsVisible by remember { mutableStateOf(false) }
    var repeatNewPassIsVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { navCtrl.navigate("forgot_password_screen") },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 20.dp, start = 20.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Perbarui Password",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Silakan perbarui password anda",
            fontWeight = FontWeight(400),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Password Baru",
            textAlign = TextAlign.Left,
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = newPass,
            onValueChange = {newPass = it},
            placeholder = { Text(text = "Masukkan password baru", color = Color.Gray)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            trailingIcon = {
                val visibilityIcon = if (newPassIsVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(
                    onClick = {
                        newPassIsVisible = !newPassIsVisible
                    }
                ) {
                    Icon(imageVector = visibilityIcon, contentDescription = null)
                }
            },
            supportingText = {
                if(newPass == ""){ }
                else if(newPass.length < 8){
                    Text(text = "Password harus terdiri dari minimal 8 karakter", color =  Color.Red)
                }
            },
            visualTransformation = if (newPassIsVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Konfirmasi Password",
            textAlign = TextAlign.Left,
            fontWeight = FontWeight(500),
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = repeatNewPass,
            onValueChange = {repeatNewPass = it},
            placeholder = { Text(text = "Masukkan password baru", color = Color.Gray)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            trailingIcon = {
                val visibilityIcon = if (repeatNewPassIsVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(
                    onClick = {
                        repeatNewPassIsVisible = !repeatNewPassIsVisible
                    }
                ) {
                    Icon(imageVector = visibilityIcon, contentDescription = null)
                }
            },
            supportingText = {
                if(repeatNewPass == ""){ }
                else if(repeatNewPass != newPass){
                    Text(text = "Password tidak sesuai", color =  Color.Red)
                }
            },
            visualTransformation = if (repeatNewPassIsVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )

        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                if (newPass == repeatNewPass) {
//                    viewModel.resetPassword(email, newPass)
                } else {
                    Toast.makeText(context, "Pengulangan password salah!", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF2752E7)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .height(50.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "Perbarui Password",
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
            )
        }
//        LaunchedEffect(isSuccess) {
//            resetPasswordMessage?.let {
//                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//            }
//            if(isSuccess == true){
//                navCtrl.navigate("success_screen/Password anda berhasil diperbarui/login_screen")
//            }
//        }
    }
}

@Preview
@Composable
private fun ResetPasswordPreview() {
    ResetPasswordScreen(navCtrl = rememberNavController(), email = "")
}