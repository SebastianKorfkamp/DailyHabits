package com.example.dailyhabits.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val startDate: Long = System.currentTimeMillis(),
    val streakCount: Int = 0,
    val lastCompletedDay: String = "", // Format: "yyyy-MM-dd"
    val isCompletedToday: Boolean = false
)