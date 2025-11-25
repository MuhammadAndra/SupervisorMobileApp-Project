package com.example.supervisormobileapp_project.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.supervisormobileapp_project.data.model.Company
import com.example.supervisormobileapp_project.data.model.Resource
import com.example.supervisormobileapp_project.ui.AuthViewModel
import com.example.supervisormobileapp_project.ui.components.CustomBottomNavBar
import com.example.supervisormobileapp_project.ui.components.OvalBackground
import com.example.supervisormobileapp_project.ui.components.UsernameCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit,
    onNavigateToReadNFC: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToPatrolList: (Int) -> Unit,
    homeViewmodel: HomeViewmodel = hiltViewModel(),
) {

    val state by homeViewmodel.companies.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        homeViewmodel.getCompaniesFromApi()
    }

    Scaffold(
        bottomBar = {
            CustomBottomNavBar(
                onHomeClick = onNavigateToHome,
                onScanClick = onNavigateToReadNFC,
                onProfileClick = onNavigateToProfile
            )
        }
    ) { innerPadding ->
        OvalBackground()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding)
                .padding(start = 20.dp, end = 20.dp, top = 15.dp)
        ) {
            //MASIH HARDCODE
            UsernameCard(fullName = "Budi Setiawan", position = "Supervisor")
            Spacer(Modifier.height(15.dp))
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
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(companies) { company ->
                            CompanyCard(
                                company = company,
                                modifier = Modifier.clickable {
                                    onNavigateToPatrolList(
                                        company.id
                                    )
                                },
                            )
                        }
                        item {}
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToHome = {},
        onNavigateToReadNFC = {},
        onNavigateToProfile = {},
        onNavigateToPatrolList = {})
}

@Composable
fun CompanyCard(modifier: Modifier = Modifier, company: Company) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(company.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Gambar Perusahaan",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column {
                Text(
                    text = company.title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                )
                Text(
                    text = company.address,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
private fun CompanyCardPreview() {
    CompanyCard(
        company = Company(
            id = 1,
            title = "Fakultas Ilmu Komputer Universitas Brawijaya",
            address = "Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtoRD2IW8OpUHuzrPbXKj9E28d-DWZhPBJLRUP2H9rKpLbKAsnDpD9ViWdTXwBdCThRzo&usqp=CAU"
        )
    )
}