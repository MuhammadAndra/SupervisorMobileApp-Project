package com.example.supervisormobileapp_project.ui.screen.scan

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.supervisormobileapp_project.NfcReaderViewModel
import com.example.supervisormobileapp_project.ui.components.CenterTopBar
import com.example.supervisormobileapp_project.ui.components.CustomBottomNavBar
import com.example.supervisormobileapp_project.ui.components.CustomDialog
import com.example.supervisormobileapp_project.ui.components.CustomTextField

@Composable
fun ReadNFCScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit,
    onNavigateToReadNFC: () -> Unit,
    onNavigateToProfile: () -> Unit,
    nfcVm: NfcReaderViewModel

) {
    //viewmodel
    val vm: ReadNFCViewModel = viewModel()
    val patrolSpot = vm.patrolSpot.collectAsStateWithLifecycle()

    val uidHex by nfcVm.uidHex.collectAsState()

    //read uid with external nfc reader
    var nfcTagUid by remember { mutableStateOf("") }
    var readNfcTagUid by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    //open dialog to show nfc not showing up in database
    var openDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uidHex) {
        if (uidHex!=null){
            vm.getPatrolSpotByNfcUid(uidHex!!)
        }
    }

    Scaffold(
        topBar = {
            CenterTopBar(
                title = "Read NFC Tag",
                color = Color(0XFF3F845F)
            )
        },
        bottomBar = {
            CustomBottomNavBar(
                onHomeClick = onNavigateToHome,
                onScanClick = onNavigateToReadNFC,
                onProfileClick = onNavigateToProfile
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 20.dp)
                .fillMaxSize()
                //read typing event for external nfc reader
                .focusRequester(focusRequester)
                .focusable()
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        val char = event.utf16CodePoint.toChar()
                        //if reading nfc uid complete
                        if (char == '\n') {
                            val reversedUid = nfcVm.reversedDecimalToHex(readNfcTagUid)
                            vm.getPatrolSpotByNfcUid(reversedUid)
                            readNfcTagUid=""
                        } else {
                            //write nfc uid
                            readNfcTagUid += char
                        }
                    }
                    true // consume event
                },
        ) {
            //if success show data
            if (patrolSpot.value?.status=="success" && patrolSpot.value?.data!=null) {
                nfcTagUid=""
                NFCTagData(
                    location =  patrolSpot.value?.data?.name?:"",
                    address = patrolSpot.value?.data?.address?:"",
                    longitude = patrolSpot.value?.data?.longitude?:"" ,
                    latitude = patrolSpot.value?.data?.latitude?:"",
                    description = patrolSpot.value?.data?.description?:"",
                    nfcTagUid = patrolSpot.value?.data?.uidNfcTag?:"",
                )
            //if error show dialog
            } else if(patrolSpot.value?.status=="error"){
                openDialog = true
                nfcTagUid=""
                vm.clearPatrolSpot()
            }else {
                //else show message to scan nfc
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        modifier = Modifier.size(90.dp),
                        imageVector = Icons.Outlined.Contactless,
                        contentDescription = "Read NFC Tag Icon",
                        tint = Color(0XFF3F845F)
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        "Tempelkan NFC Tag",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color(0XFF3F845F)
                    )
                }
            }
        }
    }
    when{
        //alert dialog to show error in nfc uid reading
        openDialog -> {
            CustomDialog(
                onDismissRequest = {
                    openDialog = false
                },
                title = {
                    Text(
                        text = "Tidak Terdeteksi",
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
                        text = "NFC Tag Tidak Terdeteksi",
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
                        color = Color(0xff3F845F),
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                    )
                }
            )
        }
    }
}

@Composable
private fun NFCTagData(
    longitude: String,
    location: String,
    address: String,
    latitude: String,
    description: String,
    nfcTagUid: String
) {
    CustomTextField(
        value = longitude,
        //onValueChange = { department = it },
        label = "Garis Bujur"
    )
    CustomTextField(
        value = location,
        //onValueChange = { nickName = it },
        label = "Nama Lokasi"
    )
    CustomTextField(
        value = address,
        //onValueChange = { nip = it },
        label = "Alamat"
    )
    CustomTextField(
        value = latitude,
        //onValueChange = { jobStatus = it },
        label = "Garis Lintang"
    )
    CustomTextField(
        value = description,
        //onValueChange = { department = it },
        label = "Deskripsi"
    )
    CustomTextField(
        value = nfcTagUid,
        //onValueChange = { department = it },
        label = "UID NFC Tag"
    )
}

@Preview
@Composable
private fun ScanNFCScreenPreview() {
    ReadNFCScreen(
        onNavigateToHome = {},
        onNavigateToReadNFC = {},
        onNavigateToProfile = {},
        nfcVm = viewModel()
    )
}