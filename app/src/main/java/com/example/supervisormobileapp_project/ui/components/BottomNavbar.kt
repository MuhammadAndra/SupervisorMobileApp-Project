package com.example.supervisormobileapp_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contactless
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomBottomNavBar(
    onHomeClick: () -> Unit,
    onScanClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .shadow(elevation = 30.dp)
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .clickable { onHomeClick() }
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 15.dp, top = 15.dp, bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF3F845F)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = "Home",
                        Modifier.size(24.dp),
                        tint = Color.White,
                    )
                }
                Spacer(Modifier.height(3.dp))
                Text("Beranda", fontSize = 12.sp)
            }


            Column(
                modifier = Modifier
                    .clickable { onProfileClick() }
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF3F845F)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "Profile",
                        Modifier.size(24.dp),
                        tint = Color.White,
                    )
                }
                Spacer(Modifier.height(3.dp))
                Text("Akun", fontSize = 12.sp)
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .offset(y = (-15).dp)
                .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp)) // ripple terbatas ke atas
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                ) { onScanClick() }) {
            Box(
                modifier = Modifier
                    // naikkan agar "floating"
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3F845F)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Contactless,
                    contentDescription = "Scan",
                    Modifier.size(50.dp),
                    tint = Color.White,
                )
            }
            Spacer(Modifier.height(3.dp))
            Text("Scan", fontSize = 12.sp)
        }
    }
}


@Preview
@Composable
private fun CustomBottomNavBarPreview() {
    CustomBottomNavBar(onHomeClick = {}, onScanClick = {}, onProfileClick = {})
}