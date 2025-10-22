package com.example.supervisormobileapp_project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.supervisormobileapp_project.NfcReaderViewModel
import com.example.supervisormobileapp_project.navigation.nav_graph.Home
import com.example.supervisormobileapp_project.navigation.nav_graph.authGraph
import com.example.supervisormobileapp_project.navigation.nav_graph.mainGraph
import com.example.supervisormobileapp_project.navigation.nav_graph.patrolSpotGraph

@Composable
fun AppNavigation(
    nfcVm: NfcReaderViewModel,
//    onEnableNfc: () -> Unit,
//    onDisableNfc: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        mainGraph(navController = navController)
        patrolSpotGraph(navController = navController, nfcVm = nfcVm,
//            onEnableNfc = { onEnableNfc() },
//            onDisableNfc = { onDisableNfc() }
        )
        authGraph(navController = navController)

    }
}