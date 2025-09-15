package com.example.dailyhabits.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dailyhabits.model.Habit
import com.example.dailyhabits.repository.HabitRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HabitViewModel(private val repository: HabitRepository) : ViewModel() {

    private val _habits = mutableStateListOf<Habit>()
    val habits: List<Habit> get() = _habits

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    init {
        loadHabits()
        initializeSampleDataIfEmpty()
    }

    private fun loadHabits() {
        viewModelScope.launch {
            repository.getAllHabits().collect { habitList ->
                _habits.clear()
                _habits.addAll(habitList)
                updateTodayStatus()
            }
        }
    }

    private fun initializeSampleDataIfEmpty() {
        viewModelScope.launch {
            // Warte kurz um sicherzustellen, dass die Daten geladen sind
            kotlinx.coroutines.delay(100)

            if (_habits.isEmpty()) {
                val sampleHabits = listOf(
                    Habit(title = "💧 2L Wasser trinken"),
                    Habit(title = "🧘 10 Min meditieren"),
                    Habit(title = "📚 30 Min lesen"),
                    Habit(title = "🚶 10.000 Schritte")
                )

                sampleHabits.forEach { habit ->
                    repository.insertHabit(habit)
                }
            }
        }
    }

    fun addHabit(title: String) {
        if (title.isNotBlank()) {
            viewModelScope.launch {
                val habit = Habit(title = title)
                repository.insertHabit(habit)
            }
        }
    }

    fun removeHabit(id: Long) {
        viewModelScope.launch {
            repository.deleteHabitById(id)
        }
    }

    fun completeHabit(habitId: Long) {
        viewModelScope.launch {
            val habit = _habits.find { it.id == habitId } ?: return@launch
            val today = getTodayString()

            if (!habit.isCompletedToday) {
                // Prüfen ob gestern auch gemacht wurde für Streak
                val yesterday = getYesterdayString()
                val newStreakCount = if (habit.lastCompletedDay == yesterday || habit.streakCount == 0) {
                    habit.streakCount + 1
                } else {
                    1 // Streak zurücksetzen
                }

                repository.updateHabitProgress(
                    id = habitId,
                    streakCount = newStreakCount,
                    lastCompletedDay = today,
                    isCompletedToday = true
                )
            }
        }
    }

    fun resetHabit(habitId: Long) {
        viewModelScope.launch {
            val habit = _habits.find { it.id == habitId } ?: return@launch
            val today = getTodayString()

            if (habit.isCompletedToday && habit.lastCompletedDay == today) {
                repository.updateHabitProgress(
                    id = habitId,
                    streakCount = maxOf(0, habit.streakCount - 1),
                    lastCompletedDay = habit.lastCompletedDay,
                    isCompletedToday = false
                )
            }
        }
    }

    private fun updateTodayStatus() {
        val today = getTodayString()
        viewModelScope.launch {
            _habits.forEach { habit ->
                if (habit.isCompletedToday != (habit.lastCompletedDay == today)) {
                    repository.updateHabitProgress(
                        id = habit.id,
                        streakCount = habit.streakCount,
                        lastCompletedDay = habit.lastCompletedDay,
                        isCompletedToday = habit.lastCompletedDay == today
                    )
                }
            }
        }
    }

    private fun getTodayString(): String {
        return dateFormat.format(Date())
    }

    private fun getYesterdayString(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return dateFormat.format(calendar.time)
    }
}

class HabitViewModelFactory(private val repository: HabitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HabitViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}