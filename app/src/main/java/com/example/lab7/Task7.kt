package com.example.lab7

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

class PageStackSignalHandler {
    var addedPages by mutableStateOf(0)
        private set
    var removedPages by mutableStateOf(0)
        private set

    fun onPageAdded() {
        addedPages++
    }

    fun onPageRemoved() {
        removedPages++
    }
}

@Composable
fun Task7(modifier: Modifier) {
    val navController = rememberNavController()

    val pageStackSignalHandler = remember { PageStackSignalHandler() }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Добавлено страниц: ${pageStackSignalHandler.addedPages}", fontSize = 20.sp)
        Text(text = "Удалено страниц: ${pageStackSignalHandler.removedPages}", fontSize = 20.sp)

        NavHost(navController = navController, startDestination = "screen/1") {
            composable("screen/{stackDepth}") { backStackEntry ->
                val stackDepth = backStackEntry.arguments?.getString("stackDepth") ?: "1"
                Screen(
                    navController = navController,
                    currentStackDepth = stackDepth.toInt(),
                    pageStackSignalHandler = pageStackSignalHandler
                )
            }
        }
    }
}

@Composable
fun Screen(
    navController: NavHostController,
    currentStackDepth: Int,
    pageStackSignalHandler: PageStackSignalHandler
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Глубина стека: $currentStackDepth", fontSize = 35.sp)

        Button(
            onClick = {
                val nextDepth = currentStackDepth + 1
                navController.navigate("screen/$nextDepth")
                pageStackSignalHandler.onPageAdded()
            },
            modifier = Modifier.padding(5.dp)
        ) {
            Text("Вперед / Добавить", fontSize = 35.sp)
        }

        Button(
            onClick = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                    pageStackSignalHandler.onPageRemoved()
                }
            }
        ) {
            Text("Назад / Удалить", fontSize = 35.sp)
        }

        BackHandler(enabled = currentStackDepth > 1) {
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
                pageStackSignalHandler.onPageRemoved()
            }
        }
    }
}