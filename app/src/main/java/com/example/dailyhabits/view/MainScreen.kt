package com.example.dailyhabits.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dailyhabits.viewmodel.HabitViewModel
import com.example.dailyhabits.viewmodel.HabitViewModelFactory

@Composable
fun MainScreen(viewModelFactory: HabitViewModelFactory) {
    var selectedTab by remember { mutableStateOf(0) }
    val viewModel: HabitViewModel = viewModel(factory = viewModelFactory)

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Habits") },
                icon = {
                    Icon(
                        Icons.Default.List,
                        contentDescription = "Habit Liste"
                    )
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Fortschritt") },
                icon = {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "Fortschritt"
                    )
                }
            )
        }

        when (selectedTab) {
            0 -> HabitListScreen(viewModel)
            1 -> HabitProgressChart(viewModel)
        }
    }
}