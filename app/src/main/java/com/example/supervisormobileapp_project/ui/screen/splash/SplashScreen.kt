package com.example.supervisormobileapp_project.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.supervisormobileapp_project.R

@Composable
fun SplashScreen(
    onNavigateToLoginOrHome: () -> Unit
) {
    var startExitAnimation by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (startExitAnimation) 0f else 1f,
        animationSpec = tween(200), label = "alpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (startExitAnimation) 0.8f else 1f,
        animationSpec = tween(200), label = "scale"
    )

    LaunchedEffect(Unit) {
        delay(1200)
        startExitAnimation = true
        delay(10)
        onNavigateToLoginOrHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF3F845F)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_siputih),
            contentDescription = "Logo Sijaga",
            modifier = Modifier
                .scale(scale)
                .alpha(alpha)
                .size(100.dp)
        )
    }
}
