package com.example.supervisormobileapp_project.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.supervisormobileapp_project.ui.screen.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object OTPChangePass

@Serializable
object ConfChangePass

fun NavGraphBuilder.authGraph(navController: NavController) {
    composable<Login> {
        LoginScreen(onNavigateToHome = {navController.navigate(Home)}, onNavigateToChangePassword = {})
    }
}