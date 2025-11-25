package com.example.supervisormobileapp_project.ui.screen.patrol_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.supervisormobileapp_project.data.model.PatrolSpot
import com.example.supervisormobileapp_project.data.model.Resource
import com.example.supervisormobileapp_project.ui.components.CenterTopBar

@Composable
fun PatrolListScreen(
    modifier: Modifier = Modifier,
    id: Int,
    onBackClick: () -> Unit,
    onNavigateToAddEditPatrol: (Int?) -> Unit,
    patrolListViewModel: PatrolListViewModel = hiltViewModel(),

    ) {
    val state by patrolListViewModel.patrolSpots.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        patrolListViewModel.getPatrolSpotsFromApi(id)
    }

    Scaffold(
        topBar = {
            CenterTopBar(
                title = "Titik Patroli Perusahaan",
                color = Color(0XFF3F845F),
                onBackClick = onBackClick
            )
        },

        ) { innerPadding ->
        when (state) {
            is Resource.Idle-> {}
            is Resource.Loading -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF3F845F))
            }

            is Resource.Error -> Toast.makeText(
                context,
                "Pengambilan Data Gagal, Coba Periksa Jaringan",
                Toast.LENGTH_LONG
            ).show()

            is Resource.Success -> {
                val companies = (state as Resource.Success).data
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { }
                    items(companies) { patrolSpot ->
                        PatrolListCard(
                            patrolSpot = patrolSpot,
                            modifier = Modifier.clickable {
                                onNavigateToAddEditPatrol(
                                    patrolSpot.id
                                )
                            }
                        )
                    }
                    item { }
                }
            }
        }


    }
}

@Preview
@Composable
private fun PatrolSpotScreenPreview() {
    PatrolListScreen(id = 10, onNavigateToAddEditPatrol = {}, onBackClick = {})
}

@Composable
fun PatrolListCard(modifier: Modifier = Modifier, patrolSpot: PatrolSpot) {
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
                text = patrolSpot.title,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
            Text(
                text = patrolSpot.address,
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
                    text = patrolSpot.latitude ?: "",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0XFF606060)
                )
                Text(
                    "|", fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0XFF606060)
                )
                Text(
                    text = patrolSpot.longitude ?: "",
                    fontWeight = FontWeight.Medium,
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
    PatrolListCard(
        patrolSpot = PatrolSpot(
            id = 1,
            title = "GKM FILKOM UB Lantai 1",
            address = "Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145",
            latitude = "-7.954699677098358",
            longitude = "112.61444010123617",
            description = "",
            nfcTagUid = null
        )
    )
}