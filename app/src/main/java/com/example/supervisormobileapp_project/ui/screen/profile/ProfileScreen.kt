package com.example.supervisormobileapp_project.ui.screen.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supervisormobileapp_project.ui.components.CenterTopBar
import com.example.supervisormobileapp_project.ui.components.CustomBottomNavBar
import com.example.supervisormobileapp_project.ui.components.CustomButton
import com.example.supervisormobileapp_project.ui.components.CustomTextField
import com.example.supervisormobileapp_project.ui.components.OvalBackground
import com.example.supervisormobileapp_project.ui.components.UsernameCard

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onReadNFCClick: () -> Unit,
    onProfileClick: () -> Unit,
    onNavigateToProfileDetail: () -> Unit
) {
    var nickName by remember { mutableStateOf("Budi") }
    var nip by remember { mutableStateOf("1954628756123679564") }
    var jobStatus by remember { mutableStateOf("Contract") }
    var department by remember { mutableStateOf("IT") }

    Scaffold(
        topBar = {
            CenterTopBar(title = "Profile")
        },
        bottomBar = {
            CustomBottomNavBar(
                onHomeClick = onHomeClick,
                onScanClick = onReadNFCClick,
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        OvalBackground()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            UsernameCard(
                modifier = Modifier.padding(all = 20.dp),
                onNavigateToProfileDetail = onNavigateToProfileDetail
            )
            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(Color.White),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                Column(
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        CustomTextField(
                            value = nickName,
                            //onValueChange = { nickName = it },
                            label = "Nama Panggilan"
                        )
                        CustomTextField(
                            value = nip,
                            //onValueChange = { nip = it },
                            label = "NIP"
                        )
                        CustomTextField(
                            value = jobStatus,
                            //onValueChange = { jobStatus = it },
                            label = "Status Kerja"
                        )
                        CustomTextField(
                            value = department,
                            //onValueChange = { department = it },
                            label = "Departemen"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clickable {onNavigateToProfileDetail()},
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Lihat Selengkapnya / Edit Profil", fontSize = 14.sp)
                            Spacer(Modifier.width(5.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Arrow Right",
                                Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
            CustomButton(
                onClick = {},
                color = Color(0XFF3F845F),
                text = "Ganti Password",
                leadingImageVector = Icons.Default.Password,
                endingImageVector = Icons.Default.ArrowForwardIos
            )
            CustomButton(
                onClick = {},
                color = Color(0XFFE25C5C),
                text = "Logout",
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        onHomeClick = {},
        onReadNFCClick = {},
        onProfileClick = {},
        onNavigateToProfileDetail = {}
    )
}