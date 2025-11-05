package com.example.supervisormobileapp_project.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.supervisormobileapp_project.ui.screen.change_password.ChangePasswordScreen
import com.example.supervisormobileapp_project.ui.screen.login.LoginScreen
import com.example.supervisormobileapp_project.ui.screen.otp.OTPScreen
import com.example.supervisormobileapp_project.ui.screen.reset_password.ResetPasswordScreen
import com.example.supervisormobileapp_project.ui.screen.reset_password.ResetPasswordScreen1
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object OTP

@Serializable
object ChangePass

@Serializable
data class ResetPass(val email: String)

fun NavGraphBuilder.authGraph(navController: NavController) {
    composable<Login> {
        LoginScreen(
            onNavigateToHome = {
                navController.popBackStack()
                navController.navigate(Home)
            },
            onNavigateToOTP = {
                navController.navigate(OTP)
            }
        )
    }
    composable<OTP> {
        OTPScreen(
            onBackClick = { navController.navigateUp() },
            onNavigateToResetPassword = { email ->
                navController.navigate(ResetPass(email = email))
            }
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

    composable<ResetPass> { navBackStackEntry ->
        val resetPass = navBackStackEntry.toRoute<ResetPass>()
        ResetPasswordScreen1(
            email = resetPass.email,
            onBackClick = { navController.navigateUp() },
            onNavigateToLogin = {
                navController.popBackStack()
                navController.navigate(Login)
            },
        )
    }
}