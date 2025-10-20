package com.example.supervisormobileapp_project.ui.screen.add_edit_patrol_spot

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.utf16CodePoint
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.ui.components.CenterTopBar
import com.example.supervisormobileapp_project.ui.components.CustomButton
import com.example.supervisormobileapp_project.ui.components.CustomDialog
import com.example.supervisormobileapp_project.ui.components.CustomTextField

@Composable
fun AddEditPatrolSpotScreen(
    modifier: Modifier = Modifier,
    id: Int?,
    onBackClick: () -> Unit,
) {
    val vm: EditPatrolSpotViewModel = viewModel()
    val patrolSpot = vm.patrolSpot.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (id != null) {
            vm.getPatrolSpotById(id)
        }
    }

    val isEdit = id != null
    var isMatching = true
    val title = if (isEdit) "Edit" else "Tambah"
    val focusRequester = remember { FocusRequester() }


    var openDialogAddNFC by remember { mutableStateOf(false) }
    var openDialogVerifyNFC by remember { mutableStateOf(false) }
    var openDialogDeleteNFC by remember { mutableStateOf(false) }
    var openDialogMatching by remember { mutableStateOf(false) }
    var openDialogVerifyTextField by remember { mutableStateOf(false) }
    //gagal dibawah ini
    var readOnlyTextField by remember { mutableStateOf(true) }

    var locationName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var nfcTagUid by remember { mutableStateOf("") }
    var verifyNfcTagUid by remember { mutableStateOf("") }

    fun verifyNFC() {
        isMatching = nfcTagUid == verifyNfcTagUid
    }

    LaunchedEffect(patrolSpot.value) {
        patrolSpot.value?.let { spot ->
            locationName = spot.name
            address = spot.address
            latitude = spot.latitude
            longitude = spot.longitude
            description = spot.description
            nfcTagUid = spot.uidNfcTag ?: ""
        }
    }

    val buttonTitle =
        if (patrolSpot.value?.uidNfcTag != null && nfcTagUid != "") "Verifikasi" else "Tambahkan"

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterTopBar(
                title = "$title Titik Patroli",
                color = Color(0XFF3F845F),
                onBackClick = onBackClick
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                CustomTextField(
                    value = locationName,
                    onValueChange = { locationName = it },
                    label = "Nama Lokasi"
                )
                CustomTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = "Alamat"
                )
                CustomTextField(
                    value = latitude,
                    label = "Garis Lintang"
                )
                CustomTextField(
                    value = longitude,
                    label = "Garis Bujur"
                )
                CustomTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = "Deskripsi"
                )
                CustomTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    value = nfcTagUid!!,
                    label = "UID NFC Tag",
                    trailingIcon = {
                        if (nfcTagUid != "") {
                            IconButton(
                                onClick = {
                                    openDialogDeleteNFC = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Delete NFC Tag UID",
                                    tint = Color(0XFFE25C5C)
                                )
                            }
                        }
                    }
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CustomButton(
                    onClick = {
                        //gagal
//                        readOnlyTextField = false
//                        focusRequester.requestFocus()
//                        openDialogAddVerifyNFC = true
                        if (patrolSpot.value?.uidNfcTag != null && nfcTagUid != "") {
                            openDialogVerifyNFC = true
                        } else {
                            openDialogAddNFC = true
                        }
                    },
                    text = "$buttonTitle NFC Tag",
                    color = Color(0XFFE4C65B),
                    leadingImageVector = Icons.Outlined.Contactless,
                    endingImageVector = Icons.Default.ArrowForwardIos
                )
                CustomButton(
                    onClick = {
                        if (locationName != "" && address != "" && latitude != "" && longitude != "" && description != "") {
                            vm.changePatrolSpot(
                                id = id!!, newSpot = PatrolSpot(
                                    companyId = patrolSpot.value!!.companyId,
                                    id = patrolSpot.value!!.id,
                                    name = locationName,
                                    address = address,
                                    latitude = latitude,
                                    longitude = longitude,
                                    description = description,
                                    uidNfcTag = nfcTagUid
                                )
                            )
                            onBackClick()
                        } else {
                            openDialogVerifyTextField = true
                        }
                    },
                    text = "$title Titik Patroli Baru",
                    color = Color(0XFF3F845F),
                )
            }
        }
        when {
            openDialogAddNFC -> {
                CustomDialog(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .focusable()
                        .onKeyEvent { event ->
                            if (event.type == KeyEventType.KeyDown) {
                                val char = event.utf16CodePoint.toChar()
                                // Enter biasanya menandakan akhir input tag
                                if (char == '\n') {
                                    openDialogAddNFC = false
                                } else {
                                    nfcTagUid += char
                                }
                            }
                            true // consume event
                        },
                    onDismissRequest = {
//                        if (isEdit && nfcTagUid != "-") openDialogMatching =
//                            true
//                        else nfcTagUid = "32:B6:DA:1C"
                        // gagal
//                        readOnlyTextField = true
//                        focusRequester.freeFocus()
                        openDialogAddNFC = false
                    },
                    title = {
                        Text(
                            text = "Tambahkan NFC Tag",
                            color = Color(0xff3F845F),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                    content = {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = Icons.Outlined.Contactless,
                            contentDescription = "Icon Contactless",
                            tint = Color(0xff3F845F)
                        )
                        Text(
                            text = "Tempelkan NFC Tag",
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

            openDialogVerifyNFC -> {
                CustomDialog(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .focusable()
                        .onKeyEvent { event ->
                            if (event.type == KeyEventType.KeyDown) {
                                val char = event.utf16CodePoint.toChar()
                                // Enter biasanya menandakan akhir input tag
                                if (char == '\n') {
                                    verifyNFC()
                                    openDialogMatching = true
                                    openDialogVerifyNFC = false
                                } else {
                                    verifyNfcTagUid += char
                                }
                            }
                            true // consume event
                        },
                    onDismissRequest = {
                        openDialogVerifyNFC = false
                    },
                    title = {
                        Text(
                            text = "$buttonTitle NFC Tag",
                            color = Color(0xff3F845F),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                    content = {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = Icons.Outlined.Contactless,
                            contentDescription = "Icon Contactless",
                            tint = Color(0xff3F845F)
                        )
                        Text(
                            text = "Tempelkan NFC Tag",
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

            openDialogDeleteNFC -> {
                CustomDialog(
                    onDismissRequest = {
                        openDialogDeleteNFC = false
                    },
                    onConfirmation = {
                        nfcTagUid = ""
                        patrolSpot.value?.uidNfcTag = null
                        openDialogDeleteNFC = false
                    },
                    title = {
                        Text(
                            text = "Yakin Hapus Data NFC Tag?",
                            color = Color(0xffE25C5C),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                    content = {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = Icons.Outlined.Contactless,
                            contentDescription = "Icon Contactless",
                            tint = Color(0xffE25C5C)
                        )
                        Text(
                            text = "Anda tidak dapat memulihkan perubahan data",
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
                            color = Color(0xff3F845F),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                    confirmButton = {
                        Text(
                            modifier = Modifier.padding(vertical = 20.dp),
                            text = "Hapus",
                            color = Color(0xffE25C5C),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    }
                )
            }

            openDialogMatching -> {
                CustomDialog(
                    onDismissRequest = {
                        openDialogMatching = false
                        verifyNfcTagUid = ""
                    },
                    title = {
                        Text(
                            text = if (isMatching) "NFC Tag Sesuai" else "NFC Tag Tidak Sesuai",
                            color = if (isMatching) Color(0xff3F845F) else Color(
                                0xffE25C5C
                            ),
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                        )
                    },
                    content = {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = if (isMatching) Icons.Outlined.CheckCircle else Icons.Outlined.Cancel,
                            contentDescription = "Icon Matching or Not",
                            tint = if (isMatching) Color(0xff3F845F) else Color(
                                0xffE25C5C
                            )
                        )
                        Text(
                            text = if (isMatching) "NFC Sesuai Dengan Database" else "NFC Tidak Sesuai Dengan Database",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = if (isMatching) Color(0xff3F845F) else Color(
                                0xffE25C5C
                            ),
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

            openDialogVerifyTextField -> {
                CustomDialog(
                    onDismissRequest = {
                        openDialogVerifyTextField = false
                    },
                    title = {
                        Text(
                            text = "Lengkapi Data",
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
                            text = "Anda dapat mengosongkan UID NFC Tag tapi harus mengisi seluruh data",
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
}

@Preview
@Composable
private fun AddEditPatrolSpotScreenPreview() {
    AddEditPatrolSpotScreen(id = null, onBackClick = {})
}