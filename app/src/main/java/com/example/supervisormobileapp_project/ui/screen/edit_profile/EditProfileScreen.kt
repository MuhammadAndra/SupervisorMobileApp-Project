package com.example.supervisormobileapp_project.ui.screen.edit_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.supervisormobileapp_project.ui.components.CenterTopBar
import com.example.supervisormobileapp_project.ui.components.CustomButton
import com.example.supervisormobileapp_project.ui.components.CustomTextField

@Composable
fun EditProfile(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    var fullName by remember { mutableStateOf("Budi Setiawan") }
    var nickName by remember { mutableStateOf("Budi") }
    var nip by remember { mutableStateOf("1954628756123679564") }
    var jobStatus by remember { mutableStateOf("Contract") }
    var position by remember { mutableStateOf("Supervisor") }
    var department by remember { mutableStateOf("IT") }
    var gender by remember { mutableStateOf("Male") }
    var religion by remember { mutableStateOf("Islam") }
    var address by remember { mutableStateOf("Jl. Simpang Bermuda No.1 Malang") }

    fun onSaveChanges(){

    }

    Scaffold(
        topBar = {
            CenterTopBar(
                title = "Edit Profil",
                color = Color(0XFF3F845F),
                onBackClick = onBackClick
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(top = 20.dp)) {
            CustomTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Nama Lengkap"
            )
            CustomTextField(
                value = nickName,
                onValueChange = { nickName = it },
                label = "Nama Panggilan"
            )
            CustomTextField(
                value = nip,
                onValueChange = { nip = it },
                label = "NIP"
            )
            CustomTextField(
                value = jobStatus,
                onValueChange = { jobStatus = it },
                label = "Status Kerja"
            )
            CustomTextField(
                value = position,
                onValueChange = { position = it },
                label = "Jabatan"
            )
            CustomTextField(
                value = department,
                onValueChange = { department = it },
                label = "Departemen"
            )
            CustomTextField(
                value = gender,
//                onValueChange = { fullName = it },
                label = "Jenis Kelamin"
            )
            CustomTextField(
                value = religion,
                onValueChange = { religion = it },
                label = "Agama"
            )
            CustomTextField(
                value = address,
                onValueChange = { address = it },
                label = "Alamat"
            )
            Spacer(Modifier.height(20.dp))
            CustomButton(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = {onSaveChanges()},
                text = "Simpan Perubahan",
                color = Color(0xff3F845F)
            )
        }
    }
}

@Preview
@Composable
private fun EditProfilePreview() {
    EditProfile(onBackClick = {})
}