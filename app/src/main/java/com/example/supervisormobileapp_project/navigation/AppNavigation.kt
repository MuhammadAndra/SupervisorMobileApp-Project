package com.example.supervisormobileapp_project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.supervisormobileapp_project.NfcReaderViewModel
import com.example.supervisormobileapp_project.navigation.nav_graph.Home
import com.example.supervisormobileapp_project.navigation.nav_graph.Login
import com.example.supervisormobileapp_project.navigation.nav_graph.Profile
import com.example.supervisormobileapp_project.navigation.nav_graph.authGraph
import com.example.supervisormobileapp_project.navigation.nav_graph.mainGraph
import com.example.supervisormobileapp_project.navigation.nav_graph.patrolSpotGraph
import com.example.supervisormobileapp_project.ui.AuthViewModel
import com.example.supervisormobileapp_project.ui.screen.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object Splash

@Composable
fun AppNavigation(
    //ini kenapa ada nfc viewModel yang dilempar kedalam?
    //kenapa ga dipanggil langsung aja di screen?
    //harusnya pake dependency injection aja sih
    //belum sempat diperbaiki
    nfcViewModel: NfcReaderViewModel,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    //kalo token null -> login else home
    val routeAfterSplash = if (authViewModel.getToken() == null) Login else Home
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onNavigateToLoginOrHome = {
                    navController.navigate(routeAfterSplash) {
                        popUpTo(Splash) { inclusive = true }
                    }
                },
            )
        }
        authGraph(navController = navController)
        mainGraph(navController = navController, nfcViewModel = nfcViewModel)
        patrolSpotGraph(navController = navController, nfcViewModel = nfcViewModel)
    }
}