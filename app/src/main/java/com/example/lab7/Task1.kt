package com.example.lab7

import androidx.compose.runtime.Composable
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.times
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.delay

@Composable
fun Task1(modifier: Modifier){
    var currentLight by remember { mutableStateOf(TrafficLightState.RED) }
    val walkingPosition = remember { Animatable(0f) }
    var screenWidth by remember { mutableStateOf(0) }

    val greenLightDuration = TrafficLightState.GREEN.duration
    val walkingAnimationDuration = (greenLightDuration * 0.9).toInt()

    LaunchedEffect(currentLight) {
        if (currentLight == TrafficLightState.GREEN) {
            walkingPosition.snapTo(0f)
            walkingPosition.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = walkingAnimationDuration,
                    easing = LinearEasing
                )
            )
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(currentLight.duration)
            currentLight = currentLight.next()
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TrafficLightCircle(color = if (currentLight == TrafficLightState.RED || currentLight == TrafficLightState.RED_YELLOW) Color.Red else Color.DarkGray)
        Spacer(modifier = Modifier.height(4.dp))
        TrafficLightCircle(color = if (currentLight == TrafficLightState.YELLOW || currentLight == TrafficLightState.RED_YELLOW) Color.Yellow else Color.DarkGray)
        Spacer(modifier = Modifier.height(4.dp))
        TrafficLightCircle(color = if (currentLight == TrafficLightState.GREEN) Color.Green else Color.DarkGray)
    }

    Row(modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom
    ){
        if (currentLight == TrafficLightState.GREEN) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .onGloballyPositioned { coordinates ->
                        screenWidth = coordinates.size.width
                    },
            ) {
                if (screenWidth > 0) {
                    WalkingIcon(walkingPosition.value, screenWidth)
                }
            }
        }
    }

}

@Composable
fun WalkingIcon(position: Float, screenWidth: Int) {
    val density = LocalDensity.current
    val screenWidthDp = with(density) { screenWidth.toDp() }
    val walkingIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.directions_walk)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            imageVector = walkingIcon,
            contentDescription = "Walking Icon",
            modifier = Modifier
                .offset(x = (position * screenWidthDp))
                .size(64.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun TrafficLightCircle(color: Color) {
    Box(
        modifier = Modifier
            .size(180.dp)
            .background(color = color, shape = CircleShape)
    )
}

enum class TrafficLightState(val duration: Long) {
    RED(3000L),
    RED_YELLOW(1000L),
    GREEN(4500L),
    YELLOW(700L);

    fun next(): TrafficLightState {
        return when (this) {
            RED -> RED_YELLOW
            RED_YELLOW -> GREEN
            GREEN -> YELLOW
            YELLOW -> RED
        }
    }
}