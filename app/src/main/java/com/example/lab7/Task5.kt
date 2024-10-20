package com.example.lab7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task5(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        CustomButton()
        CustomButton(text = "Custom Text", buttonColor = Color.Red)
    }
}

@Composable
fun CustomButton(
    text: String = "Default Text",
    buttonColor: Color = Color.Black
){
    Button(
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = buttonColor
        ),
        onClick = {}
    ){
        Text(text = text, fontSize = 30.sp)
    }
}