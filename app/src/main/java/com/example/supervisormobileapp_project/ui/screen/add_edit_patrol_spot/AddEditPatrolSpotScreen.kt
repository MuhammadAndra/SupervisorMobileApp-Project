package com.example.supervisormobileapp_project.ui.screen.add_edit_patrol_spot

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val isEdit = id != null
    var isMatching = true
    val title = if (isEdit) "Edit" else "Tambah"

    var openDialogAddVerifyNFC by remember { mutableStateOf(false) }
    var openDialogDeleteNFC by remember { mutableStateOf(false) }
    var openDialogMatching by remember { mutableStateOf(false) }

    fun verifyNFC() {
        isMatching = !isMatching
    }

    var locationName by remember { mutableStateOf("Gedung F FILKOM UB Lantai 4") }
    var address by remember { mutableStateOf("Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145") }
    var latitude by remember { mutableStateOf("-7.953928356235202") }
    var longitude by remember { mutableStateOf("112.61452104116398") }
    var description by remember { mutableStateOf("Ad ut voluptatem ut qui rerum qui illum. Earum quia exercitationem exercitationem voluptas tempore aliquid. Ad ullam dolorum id. Voluptatem facere aut quia sed dignissimos quae.") }
    var nfcTagUid by remember { mutableStateOf(if (isEdit) "32:B6:DA:1C" else "-") }
    val buttonTitle = if (isEdit && nfcTagUid != "-") "Verifikasi" else "Tambahkan"

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
                            IconButton(onClick = {
                                openDialogDeleteNFC = true
                            }) {
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
                    onClick = { openDialogAddVerifyNFC = true },
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
        when {
            openDialogAddVerifyNFC -> {
                CustomDialog(
                    onDismissRequest = {
                        if (isEdit && nfcTagUid!="-") openDialogMatching = true
                        else nfcTagUid = "32:B6:DA:1C"
                        openDialogAddVerifyNFC = false
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
                        nfcTagUid = "32:B6:DA:1C"
                        openDialogDeleteNFC = false
                    },
                    onConfirmation = {
                        nfcTagUid = "-"
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
                        verifyNFC()
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
        }

    }
}

@Preview
@Composable
private fun AddEditPatrolSpotScreenPreview() {
    AddEditPatrolSpotScreen(id = null, onBackClick = {})
}