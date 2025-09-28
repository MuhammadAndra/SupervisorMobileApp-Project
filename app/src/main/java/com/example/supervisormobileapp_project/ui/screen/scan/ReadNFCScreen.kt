package com.example.supervisormobileapp_project.ui.screen.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contactless
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.supervisormobileapp_project.ui.components.CenterTopBar
import com.example.supervisormobileapp_project.ui.components.CustomBottomNavBar
import com.example.supervisormobileapp_project.ui.components.CustomTextField

@Composable
fun ReadNFCScreen(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onReadNFCClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var reading by remember { mutableStateOf(true) }
    var location by remember { mutableStateOf("GKM FILKOM UB Lantai 1") }
    var address by remember { mutableStateOf("Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145") }
    var latitude by remember { mutableStateOf("-7.954699677098358") }
    var longitude by remember { mutableStateOf("112.61444010123617") }
    var description by remember { mutableStateOf("Ad ut voluptatem ut qui rerum qui illum. Earum quia exercitationem exercitationem voluptas tempore aliquid. Ad ullam dolorum id. Voluptatem facere aut quia sed dignissimos quae.") }
    var nfcTagUid by remember { mutableStateOf("32:B6:DA:1C") }
    var addressedSupervisor by remember { mutableStateOf("112.61444010123617") }

    Scaffold(
        topBar = {
            CenterTopBar(
                title = "Read NFC Tag",
                color = Color(0XFF3F845F)
            )
        },
        bottomBar = {
            CustomBottomNavBar(
                onHomeClick = onHomeClick,
                onScanClick = onReadNFCClick,
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 20.dp)
                .fillMaxSize(),
        ) {
            if (reading) {
                NFCTagData(
                    longitude = longitude,
                    location = location,
                    address = address,
                    latitude = latitude,
                    description = description,
                    nfcTagUid = nfcTagUid
                )
            } else {
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
    ReadNFCScreen(onHomeClick = {}, onReadNFCClick = {}, onProfileClick = {})
}