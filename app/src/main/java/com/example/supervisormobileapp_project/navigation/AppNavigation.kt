package com.example.supervisormobileapp_project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.supervisormobileapp_project.navigation.nav_graph.Home
import com.example.supervisormobileapp_project.navigation.nav_graph.mainGraph
import com.example.supervisormobileapp_project.navigation.nav_graph.patrolSpotGraph

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        mainGraph(navController = navController)
        patrolSpotGraph(navController = navController)

    }
}