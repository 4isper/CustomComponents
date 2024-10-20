package com.example.lab7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun Task6(modifier: Modifier) {
    var isRunning by remember { mutableStateOf(false) }
    var timeInSeconds by remember { mutableIntStateOf(0) }

    LaunchedEffect(isRunning) {
        while (isRunning){
            delay(1000L)
            timeInSeconds++
        }
    }

    val hours = timeInSeconds / 36000
    val minutes = (timeInSeconds % 3600) / 60
    val seconds = timeInSeconds % 60

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)
    ) {
        TimerDisplay(hours, minutes, seconds)
        Button(
            onClick = { isRunning = !isRunning}
        ) {
            Text(text = if (isRunning) "Остановить" else "Запустить",
                fontSize = 30.sp
            )
        }
    }
}

@Composable
fun TimerDisplay(hours: Int, minutes: Int, seconds: Int) {
    Row {
        TimeComponent(time = hours)
        Text(text = ":", fontSize = 70.sp)
        TimeComponent(time = minutes)
        Text(text = ":", fontSize = 70.sp)
        TimeComponent(time = seconds)
    }
}

@Composable
fun TimeComponent(time: Int){
    Text(
        text = time.toString().padStart(2, '0'),
        fontSize = 70.sp,
        fontWeight = FontWeight.W600
    )
}
