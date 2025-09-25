package com.example.supervisormobileapp_project.ui.components

import androidx.compose.foundation.background
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

@Composable
fun UsernameCard(modifier: Modifier = Modifier) {
    ElevatedCard(
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        modifier = Modifier
            .fillMaxWidth()
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
                    "Budi Setiawan",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp
                )
                Text(
                    "Supervisor",
                    color = Color.White,
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
    UsernameCard()
}