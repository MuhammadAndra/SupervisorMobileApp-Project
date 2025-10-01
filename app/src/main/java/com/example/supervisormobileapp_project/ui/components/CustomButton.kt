package com.example.supervisormobileapp_project.ui.components

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//custom button
//tambah icon diawal diakhir (opsional)
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    color: Color,
    leadingImageVector: ImageVector? = null,
    endingImageVector: ImageVector? = null
) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(8.dp),
                color = color
            )
            .clickable {onClick()},
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (leadingImageVector != null) {
                Icon(
                    imageVector = leadingImageVector,
                    contentDescription = "Leading Icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            if (endingImageVector != null) {
                Icon(
                    imageVector = endingImageVector,
                    contentDescription = "Ending Icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomButtonPrev() {
    CustomButton(
        onClick = {},
        color = Color(0XFF3F845F),
        text = "Ganti Password",
        leadingImageVector = Icons.Default.Password,
        endingImageVector = Icons.Default.ArrowForwardIos
    )
}