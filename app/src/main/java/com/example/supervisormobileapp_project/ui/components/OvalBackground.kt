package com.example.supervisormobileapp_project.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun OvalBackground() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clipToBounds()
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            // Ukuran elips besar
            val ellipseDesignWidth = 306f
            val ellipseDesignHeight = 900f

            val scaleFactor = canvasWidth / ellipseDesignWidth

            val scaleMultiplier =
                2f // Semakin besar, semakin “lepas” oval dari layar
            val scaledWidth = canvasWidth * scaleMultiplier
            val scaledHeight = ellipseDesignHeight * scaleFactor * 0.5f

            drawOval(
                color = Color(0xFF3F845F),
                topLeft = Offset(
                    x = -(scaledWidth - canvasWidth) / 2f, // geser agar tengah oval tetap di tengah layar
                    y = canvasHeight - scaledHeight
                ),
                size = Size(scaledWidth, scaledHeight)
            )
        }
    }
}

