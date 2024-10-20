package com.example.lab7

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task3(modifier: Modifier) {
    var isClicked by remember { mutableStateOf(false) }

    val offsetY by animateDpAsState(targetValue = if (isClicked) 500.dp else 0.dp)
    val rotation by animateFloatAsState(targetValue = if (isClicked) 180f else 0f)
    val color by animateColorAsState(targetValue = if (isClicked) Color.Red else Color.Black)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Hello World!",
            modifier = Modifier
                .offset(y = offsetY)
                .clickable { isClicked = !isClicked }
                .graphicsLayer {
                    rotationZ = rotation
                },
            fontSize = 50.sp,
            color = color
        )
    }
}