package com.example.supervisormobileapp_project.navigation.nav_graph

import kotlinx.serialization.Serializable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.supervisormobileapp_project.ui.screen.home.HomeScreen
import com.example.supervisormobileapp_project.ui.screen.profile.ProfileScreen
import com.example.supervisormobileapp_project.ui.screen.scan.ReadNFCScreen

@Serializable
object Home

@Serializable
object Profile

@Serializable
object EditProfile

@Serializable
object ReadNFC


fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable<Home> {
        HomeScreen(
            onHomeClick = {},
            onReadNFCClick = {
                navController.popBackStack()
                navController.navigate(ReadNFC)
            },
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
            onReadNFCClick = {
                navController.popBackStack()
                navController.navigate(ReadNFC)
            },
            onProfileClick = {},
            onNavigateToProfileDetail = {}
        )
    }

    composable<ReadNFC> {
        ReadNFCScreen(
            onHomeClick = {
                navController.popBackStack()
                navController.navigate(Home)
            },
            onReadNFCClick = {},
            onProfileClick = {
                navController.popBackStack()
                navController.navigate(Profile)
            },
        )
    }
}