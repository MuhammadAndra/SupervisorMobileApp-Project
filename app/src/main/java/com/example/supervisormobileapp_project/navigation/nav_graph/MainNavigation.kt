package com.example.supervisormobileapp_project.navigation.nav_graph

import kotlinx.serialization.Serializable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.supervisormobileapp_project.ui.screen.home.HomeScreen
import com.example.supervisormobileapp_project.ui.screen.profile.ProfileScreen

@Serializable
object Home

@Serializable
object Profile

@Serializable
object EditProfile

@Serializable
object ReadNFCTag


fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable<Home> {
        HomeScreen(
            onHomeClick = {},
            onScanClick = {},
            onProfileClick = {
                navController.popBackStack()
                navController.navigate(Profile)
            }
        )
    }

    composable<Profile> {
        ProfileScreen(
            onHomeClick = {
                navController.popBackStack()
                navController.navigate(Home)
            },
            onScanClick = {},
            onProfileClick = {},
            onNavigateToProfileDetail = {}
        )
    }
}