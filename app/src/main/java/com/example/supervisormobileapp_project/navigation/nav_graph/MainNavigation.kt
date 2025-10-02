package com.example.supervisormobileapp_project.navigation.nav_graph

import kotlinx.serialization.Serializable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.supervisormobileapp_project.ui.screen.edit_profile.EditProfile
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
            onNavigateToHome = {},
            onNavigateToReadNFC = {
                navController.popBackStack()
                navController.navigate(ReadNFC)
            },
            onNavigateToProfile = {
                navController.popBackStack()
                navController.navigate(Profile)
            },
            onNavigateToPatrolList = { id->
                navController.navigate(PatrolList(id = id))
            }
        )
    }

    composable<Profile> {
        ProfileScreen(
            onNavigateToHome = {
                navController.popBackStack()
                navController.navigate(Home)
            },
            onNavigateToReadNFC = {
                navController.popBackStack()
                navController.navigate(ReadNFC)
            },
            onNavigateToProfile = {},
            onNavigateToProfileDetail = {navController.navigate(EditProfile)}
        )
    }
    composable<EditProfile> {
        EditProfile(onBackClick = { navController.popBackStack() })
    }

    composable<ReadNFC> {
        ReadNFCScreen(
            onNavigateToHome = {
                navController.popBackStack()
                navController.navigate(Home)
            },
            onNavigateToReadNFC = {},
            onNavigateToProfile = {
                navController.popBackStack()
                navController.navigate(Profile)
            },
        )
    }
}