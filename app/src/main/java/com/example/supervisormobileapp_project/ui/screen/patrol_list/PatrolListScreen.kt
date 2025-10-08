package com.example.supervisormobileapp_project.ui.screen.patrol_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.supervisormobileapp_project.ui.components.CenterTopBar

@Composable
fun PatrolListScreen(
    modifier: Modifier = Modifier,
    id: Int,
    onBackClick: () -> Unit,
    onNavigateToAddEditPatrol: (Int?) -> Unit
) {
    Scaffold(
        topBar = {
            CenterTopBar(
                title = "Titik Patroli Perusahaan",
                color = Color(0XFF3F845F),
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateToAddEditPatrol(
                        null
                    )
                },
                containerColor = Color(0XFF3F845F),
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Icon Add Patrol Spot",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {  }
            items(count = 10) { count ->
                PatrolListCard(
                    modifier = Modifier.clickable {
                        onNavigateToAddEditPatrol(
                            count
                        )
                    }
                )
            }
            item {  }
        }

    }
}

@Preview
@Composable
private fun PatrolSpotScreenPreview() {
    PatrolListScreen(id = 10, onNavigateToAddEditPatrol = {}, onBackClick = {})
}

@Composable
fun PatrolListCard(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        colors = CardDefaults.elevatedCardColors(Color.White),
        shape = RoundedCornerShape(8.dp),
        ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "GKM FILKOM UB Lantai 1",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Text(
                "Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "-7.954699677098358", fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0XFF606060)
                )
                Text(
                    "|", fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0XFF606060)
                )
                Text(
                    "112.61444010123617", fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0XFF606060)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PatrolListCardPreview() {
    PatrolListCard()
}