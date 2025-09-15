package com.example.dailyhabits.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Query("SELECT * FROM habits ORDER BY id DESC")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getHabitById(id: Long): HabitEntity?

    @Insert
    suspend fun insertHabit(habit: HabitEntity): Long

    @Update
    suspend fun updateHabit(habit: HabitEntity)

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Query("DELETE FROM habits WHERE id = :id")
    suspend fun deleteHabitById(id: Long)

    @Query("UPDATE habits SET streakCount = :streakCount, lastCompletedDay = :lastCompletedDay, isCompletedToday = :isCompletedToday WHERE id = :id")
    suspend fun updateHabitProgress(
        id: Long,
        streakCount: Int,
        lastCompletedDay: String,
        isCompletedToday: Boolean
    )
}