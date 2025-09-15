package com.example.dailyhabits.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.dailyhabits.model.Habit
import java.text.SimpleDateFormat
import java.util.*

class HabitViewModel : ViewModel() {

    private val _habits = mutableStateListOf<Habit>()
    val habits: List<Habit> get() = _habits

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    init {
        // Beispiel-Daten mit besseren Namen
        _habits.addAll(
            listOf(
                Habit(title = "💧 2L Wasser trinken"),
                Habit(title = "🧘 10 Min meditieren"),
                Habit(title = "📚 30 Min lesen"),
                Habit(title = "🚶 10.000 Schritte")
            )
        )
        updateTodayStatus()
    }

    fun addHabit(title: String) {
        if (title.isNotBlank()) {
            _habits.add(Habit(title = title))
        }
    }

    fun removeHabit(id: Long) {
        _habits.removeAll { it.id == id }
    }

    fun completeHabit(habitId: Long) {
        val habit = _habits.find { it.id == habitId } ?: return
        val today = getTodayString()

        if (!habit.isCompletedToday) {
            // Prüfen ob gestern auch gemacht wurde für Streak
            val yesterday = getYesterdayString()
            if (habit.lastCompletedDay == yesterday || habit.streakCount == 0) {
                habit.streakCount += 1
            } else {
                habit.streakCount = 1 // Streak zurücksetzen
            }

            habit.lastCompletedDay = today
            habit.isCompletedToday = true
        }
    }

    fun resetHabit(habitId: Long) {
        val habit = _habits.find { it.id == habitId } ?: return
        val today = getTodayString()

        if (habit.isCompletedToday && habit.lastCompletedDay == today) {
            habit.isCompletedToday = false
            habit.streakCount = maxOf(0, habit.streakCount - 1)
        }
    }

    private fun updateTodayStatus() {
        val today = getTodayString()
        _habits.forEach { habit ->
            habit.isCompletedToday = habit.lastCompletedDay == today
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