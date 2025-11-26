package com.example.supervisormobileapp_project.ui.screen.edit_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.supervisormobileapp_project.data.model.Supervisor
import com.example.supervisormobileapp_project.ui.components.CenterTopBar
import com.example.supervisormobileapp_project.ui.components.CustomButton
import com.example.supervisormobileapp_project.ui.components.CustomTextField
import com.example.supervisormobileapp_project.ui.screen.profile.ProfileViewModel

@Composable
fun EditProfile(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    vm: ProfileViewModel = hiltViewModel(),
) {
    val supervisor = vm.supervisor.collectAsStateWithLifecycle()

    val employeeProfile by vm.employeeProfile.collectAsState()

    LaunchedEffect(Unit) {
//        vm.getSupervisor()
        vm.getEmployeeProfile()
    }
    var fullName by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var nip by remember { mutableStateOf("") }
    var jobStatus by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var religion by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

//    LaunchedEffect(supervisor.value) {
//        supervisor.value?.let {
//            fullName = it.fullName
//            position = it.position
//            nickname = it.nickname
//            nip = it.nip
//            jobStatus = it.jobStatus
//            department = it.department
//            gender = it.gender
//            religion = it.religion
//            address = it.address
//        }
//    }

    LaunchedEffect(employeeProfile) {
        employeeProfile?.data.let {
            fullName = it?.fullName ?: ""
            position = it?.position ?: ""
            nickname = it?.nickname ?: ""
            nip = it?.idNumber ?: ""
            jobStatus = it?.employmentStatus ?: ""
            department = it?.department ?: ""
            gender = it?.gender ?:""
            religion = it?.religion ?:""
            address = it?.address ?:""
        }
    }


    fun onSaveChanges() {
        vm.editSupervisorData(
            Supervisor(
                fullName = fullName,
                nickname = nickname,
                nip = nip,
                jobStatus = jobStatus,
                position = position,
                department = department,
                gender = gender,
                religion = religion,
                address = address
            )
        )
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 20.dp)
        ) {
            CustomTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Nama Lengkap"
            )
            CustomTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = "Nama Panggilan"
            )
            CustomTextField(
                value = nip,
                onValueChange = { nip = it },
                label = "NIP"
            )
            CustomTextField(
                value = jobStatus,
                label = "Status Kerja"
            )
            CustomTextField(
                value = position,
                label = "Jabatan"
            )
            CustomTextField(
                value = department,
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
                onClick = {
                    onSaveChanges()
                    onBackClick()
                },
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