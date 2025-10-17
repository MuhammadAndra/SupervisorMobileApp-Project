package com.example.supervisormobileapp_project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import okhttp3.internal.wait


//Username Card
//kalo ga diisi onNavigate jadi transparan + gabisa diklik
@Composable
fun UsernameCard(
    fullName:String,
    position:String,
    modifier: Modifier = Modifier,
    onNavigateToProfileDetail: (() -> Unit)? = null,
) {
    val cardColors = if (onNavigateToProfileDetail == null) {
        CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    } else {
        CardDefaults.elevatedCardColors(Color.White)
    }
    val cardElevation = if (onNavigateToProfileDetail == null) {
        CardDefaults.elevatedCardElevation(0.dp)
    } else {
        CardDefaults.elevatedCardElevation(5.dp)
    }

    ElevatedCard(
        colors = cardColors,
        elevation = cardElevation,
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (onNavigateToProfileDetail != null) {
                    Modifier.clickable { onNavigateToProfileDetail() }
                } else {
                    Modifier // tidak ada clickable
                }
            )
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Color(0XFFCACACA),
                        shape = CircleShape
                    )
                    .padding(13.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person icon",
                    tint = Color(0XFFA9A9A9),
                    modifier = Modifier
                        .size(50.dp)
                )
            }
            Column {
                Text(
                    fullName,
                    color = if (onNavigateToProfileDetail == null) Color.White else Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp
                )
                Text(
                    position,
                    color = if (onNavigateToProfileDetail == null) Color.White else Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun UserNameCardPreview() {
    UsernameCard(fullName = "Budi Setiawan", position = "Supervisor")
}