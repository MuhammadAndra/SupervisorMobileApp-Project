package com.example.supervisormobileapp_project.ui.screen.add_edit_patrol_spot

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.supervisormobileapp_project.NfcReaderViewModel
import com.example.supervisormobileapp_project.data.model.EditPatrolSpotResponse
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.Resource
import com.example.supervisormobileapp_project.ui.components.CenterTopBar
import com.example.supervisormobileapp_project.ui.components.CustomButton
import com.example.supervisormobileapp_project.ui.components.CustomDialog
import com.example.supervisormobileapp_project.ui.components.CustomLoadingButton
import com.example.supervisormobileapp_project.ui.components.CustomTextField

@Composable
fun AddEditPatrolSpotScreen(
    modifier: Modifier = Modifier,
    id: Int?,
    onBackClick: () -> Unit,
    nfcVm: NfcReaderViewModel,
    editPatrolSpotViewModel: EditPatrolSpotViewModel = hiltViewModel(),

//    onEnableNfc: () -> Unit,
//    onDisableNfc: () -> Unit
) {
    val uidHex by nfcVm.uidHex.collectAsState()
    val patrolSpot =
        editPatrolSpotViewModel.patrolSpot.collectAsStateWithLifecycle()

    val editPatrolSpotState by
    editPatrolSpotViewModel.editPatrolSpotResponse.collectAsState()
    val context = LocalContext.current
//    val vm: EditPatrolSpotViewModel = viewModel()
//    val patrolSpot = vm.patrolSpot.collectAsStateWithLifecycle()

    //initial data fetching for patrol spot detail
    LaunchedEffect(Unit) {
        if (id != null) {
//            editPatrolSpotViewModel.getPatrolSpotById(id)
            editPatrolSpotViewModel.getPatrolSpotByIdFromApi(id)
        }
    }

    //not needed
    val isEdit = id != null
    //verify nfc uid with database
//    var isMatching = true
    var isMatching by remember { mutableStateOf(true) }
    //not needed
    val title = if (isEdit) "Edit" else "Tambah"
    //for reading uid with external nfc reader
    val focusRequester = remember { FocusRequester() }

    //open closed dialog
    var openDialogAddNFC by remember { mutableStateOf(false) }
    var openDialogVerifyNFC by remember { mutableStateOf(false) }
    var openDialogDeleteNFC by remember { mutableStateOf(false) }
    var openDialogMatching by remember { mutableStateOf(false) }
    var openDialogVerifyTextField by remember { mutableStateOf(false) }


    //patrol spot detail data
    var locationName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var nfcTagUid by remember { mutableStateOf("") }

    //used to contain newly scanned nfc tag uid to verify
    var verifyNfcTagUid by remember { mutableStateOf("") }
    //temp var to contain raw nfc uid from external nfc reader
    var readNfcTagUid by remember { mutableStateOf("") }

    //check if patrol spot data from database equal to newly scanned nfc tag
    fun verifyNFC() {
        isMatching = nfcTagUid == verifyNfcTagUid
    }

    //display patrol spot detail data
    LaunchedEffect(patrolSpot.value) {
        patrolSpot.value?.let { spot ->
            locationName = spot.title
            address = spot.address
            latitude = spot.latitude ?: "-"
            longitude = spot.longitude ?: "-"
            description = spot.description ?: "-"
            nfcTagUid = spot.nfcTagUid ?: ""
        }
    }

    //check the button usage whether to verify or add nfc tag
    val buttonTitle =
        if (patrolSpot.value?.nfcTagUid != null && nfcTagUid != "") "Verifikasi" else "Tambahkan"

    LaunchedEffect(editPatrolSpotState) {
        when(val state = editPatrolSpotState) {
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    "Edit Gagal: NFC Tag TIDAK BOLEH SAMA dengan titik lain",
                    Toast.LENGTH_LONG
                ).show()
            }
            is Resource.Success -> {
                if (state.data.status == "success") {
                    onBackClick()
                }
            }
            else -> {}
        }
    }

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
                    value = nfcTagUid,
                    label = "UID NFC Tag",
                    onValueChange = { nfcTagUid = it },
                    trailingIcon = {
                        //if nfc tag uid not null display clear text button
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

                        //if dialog from database and local is empty. if not open dialog to verify
                        if (patrolSpot.value?.nfcTagUid != null && nfcTagUid != "") {
                            nfcVm.clearUid()
                            openDialogVerifyNFC = true
                        } else {
                            nfcVm.clearUid()
                            openDialogAddNFC = true
                        }
                    },
                    text = "$buttonTitle NFC Tag",
                    color = Color(0XFFE4C65B),
                    leadingImageVector = Icons.Outlined.Contactless,
                    endingImageVector = Icons.Default.ArrowForwardIos
                )
                //loading button
                CustomLoadingButton(
                    onClick = {
                        if (
                            locationName != "" &&
                            address != "" &&
                            latitude != "" &&
                            longitude != "" &&
                            description != ""
                        ) {
                            editPatrolSpotViewModel.changePatrolSpotFromApi(
                                id = id!!, editedPatrolSpot = PatrolSpot(
                                    companyId = patrolSpot.value!!.companyId,
                                    id = patrolSpot.value!!.id,
                                    title = locationName,
                                    address = address,
                                    latitude = latitude,
                                    longitude = longitude,
                                    description = description,
                                    nfcTagUid = nfcTagUid
                                )
                            )

                        } else {
                            openDialogVerifyTextField = true
                        }
                    },
                    color = Color(0XFF3F845F),
                    content = {
                        if (editPatrolSpotState !is Resource.Loading) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Edit Titik Patroli",
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                )
            }
        }


    }

    when {
        openDialogAddNFC -> {
            LaunchedEffect(uidHex) {
                uidHex?.let {
                    nfcTagUid = it
                    nfcVm.clearUid()
                    openDialogAddNFC = false
//                    onDisableNfc()
                }
            }
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
                                nfcTagUid =
                                    nfcVm.reversedDecimalToHex(readNfcTagUid)
                                readNfcTagUid = ""
                            } else {
//                                nfcTagUid += char
                                readNfcTagUid += char
                            }
                        }
                        true // consume event
                    },
                onDismissRequest = {
//                        if (isEdit && nfcTagUid != "-") openDialogMatching =
//                            true
//                        else nfcTagUid = "32:B6:DA:1C"
                    // failed attempt
//                        readOnlyTextField = true
//                        focusRequester.freeFocus()
                    openDialogAddNFC = false
                    nfcVm.clearUid()
//                    onDisableNfc()
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
            LaunchedEffect(uidHex) {
                if (uidHex != null) {
                    verifyNfcTagUid = uidHex!!
                    verifyNFC()
                    openDialogMatching = true
                    openDialogVerifyNFC = false
                    nfcVm.clearUid()
//                    onDisableNfc()
                }
            }
            CustomDialog(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .focusable()
                    .onKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            val char = event.utf16CodePoint.toChar()
                            // Enter biasanya menandakan akhir input tag
                            if (char == '\n') {
                                verifyNfcTagUid =
                                    nfcVm.reversedDecimalToHex(readNfcTagUid)
                                verifyNFC()
                                openDialogMatching = true
                                openDialogVerifyNFC = false
                                readNfcTagUid = ""
                            } else {
//                                verifyNfcTagUid += char
                                readNfcTagUid += char
                            }
                        }
                        true // consume event
                    },
                onDismissRequest = {
                    openDialogVerifyNFC = false
                    nfcVm.clearUid()
//                    onDisableNfc()
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
                    patrolSpot.value?.nfcTagUid = null
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
                    nfcVm.clearUid()
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

@Preview
@Composable
private fun AddEditPatrolSpotScreenPreview() {
    AddEditPatrolSpotScreen(
        id = null,
        onBackClick = {},
        nfcVm = viewModel(),
//        onEnableNfc = {},
//        onDisableNfc = {}
    )
}