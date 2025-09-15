package com.example.dailyhabits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dailyhabits.data.HabitDatabase
import com.example.dailyhabits.repository.HabitRepository
import com.example.dailyhabits.view.MainScreen
import com.example.dailyhabits.viewmodel.HabitViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var repository: HabitRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Database und Repository initialisieren
        val database = HabitDatabase.getDatabase(this)
        repository = HabitRepository(database.habitDao())

        setContent {
            MaterialTheme {
                Surface {
                    MainScreen(
                        viewModelFactory = HabitViewModelFactory(repository)
                    )
                }
            }
        }
    }
}