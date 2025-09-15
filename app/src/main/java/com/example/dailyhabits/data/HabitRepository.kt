package com.example.dailyhabits.repository

import com.example.dailyhabits.data.HabitDao
import com.example.dailyhabits.data.HabitEntity
import com.example.dailyhabits.model.Habit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitRepository(private val habitDao: HabitDao) {

    fun getAllHabits(): Flow<List<Habit>> {
        return habitDao.getAllHabits().map { entities ->
            entities.map { it.toHabit() }
        }
    }

    suspend fun getHabitById(id: Long): Habit? {
        return habitDao.getHabitById(id)?.toHabit()
    }

    suspend fun insertHabit(habit: Habit): Long {
        return habitDao.insertHabit(habit.toEntity())
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit.toEntity())
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit.toEntity())
    }

    suspend fun deleteHabitById(id: Long) {
        habitDao.deleteHabitById(id)
    }

    suspend fun updateHabitProgress(
        id: Long,
        streakCount: Int,
        lastCompletedDay: String,
        isCompletedToday: Boolean
    ) {
        habitDao.updateHabitProgress(id, streakCount, lastCompletedDay, isCompletedToday)
    }
}

// Extension functions für Konvertierung zwischen Entity und Domain Model
private fun HabitEntity.toHabit(): Habit {
    return Habit(
        id = this.id,
        title = this.title,
        startDate = this.startDate,
        streakCount = this.streakCount,
        lastCompletedDay = this.lastCompletedDay,
        isCompletedToday = this.isCompletedToday
    )
}

private fun Habit.toEntity(): HabitEntity {
    return HabitEntity(
        id = this.id,
        title = this.title,
        startDate = this.startDate,
        streakCount = this.streakCount,
        lastCompletedDay = this.lastCompletedDay,
        isCompletedToday = this.isCompletedToday
    )
}