package com.example.dailyhabits.model

data class Habit(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val startDate: Long = System.currentTimeMillis(),
    var streakCount: Int = 0,
    var lastCompletedDay: String = "", // Format: "yyyy-MM-dd"
    var isCompletedToday: Boolean = false
)