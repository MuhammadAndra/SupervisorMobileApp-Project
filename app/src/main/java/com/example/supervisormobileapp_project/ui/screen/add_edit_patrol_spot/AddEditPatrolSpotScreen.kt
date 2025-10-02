package com.example.supervisormobileapp_project.ui.screen.add_edit_patrol_spot

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contactless
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.material.icons.sharp.Cancel
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun AddEditPatrolSpotScreen(
    modifier: Modifier = Modifier,
    id: Int?,
    onBackClick: () -> Unit,
) {
    val title = if (id == null) "Tambah" else "Edit"
    val buttonTitle = if (id == null) "Tambahkan" else "Verifikasi"

    var locationName by remember { mutableStateOf("Gedung F FILKOM UB Lantai 4") }
    var address by remember { mutableStateOf("Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145") }
    var latitude by remember { mutableStateOf("-7.953928356235202") }
    var longitude by remember { mutableStateOf("112.61452104116398") }
    var description by remember { mutableStateOf("Ad ut voluptatem ut qui rerum qui illum. Earum quia exercitationem exercitationem voluptas tempore aliquid. Ad ullam dolorum id. Voluptatem facere aut quia sed dignissimos quae.") }
    var nfcTagUid by remember { mutableStateOf(if (id == null) "-" else "32:B6:DA:1C") }

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
                    onValueChange = { latitude = it },
                    label = "Garis Lintang"
                )
                CustomTextField(
                    value = longitude,
                    onValueChange = { longitude = it },
                    label = "Garis Bujur"
                )
                CustomTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = "Deskripsi"
                )
                CustomTextField(
                    value = nfcTagUid,
                    label = "UID NFC Tag",
                    trailingIcon = {
                        if (nfcTagUid != "-") {
                            IconButton(onClick = {nfcTagUid = "-"}) {
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
                    onClick = {nfcTagUid = "32:B6:DA:1C"},
                    text = "$buttonTitle NFC Tag",
                    color = Color(0XFFE4C65B),
                    leadingImageVector = Icons.Outlined.Contactless,
                    endingImageVector = Icons.Default.ArrowForwardIos
                )
                CustomButton(
                    onClick = { onBackClick() },
                    text = "$title Titik Patroli Baru",
                    color = Color(0XFF3F845F),
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