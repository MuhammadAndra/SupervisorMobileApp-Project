package com.example.supervisormobileapp_project.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.supervisormobileapp_project.ui.screen.change_password.ChangePasswordScreen
import com.example.supervisormobileapp_project.ui.screen.login.LoginScreen
import com.example.supervisormobileapp_project.ui.screen.otp.OTPScreen
import com.example.supervisormobileapp_project.ui.screen.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object OTP

@Serializable
object ChangePass

@Serializable
object ResetPass

fun NavGraphBuilder.authGraph(navController: NavController) {
    composable<Login> {
        LoginScreen(
            onNavigateToHome = {
                navController.popBackStack()
                navController.navigate(Home)
            },
            onNavigateToChangePassword = {}
        )
    }
    composable<OTP> {
        OTPScreen(
            onBackClick = { navController.navigateUp() },
            onNavigateToChangePassword = { navController.navigate(ChangePass) }
        )
    }
    composable<ChangePass> {
        ChangePasswordScreen(
            onBackClick = { navController.navigateUp() },
            onNavigateToLogin = {
                navController.popBackStack()
                navController.navigate(Login)
            }
        )
    }

    composable<ResetPass> {

    }
}