package com.ivtogi.workouttimer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ivtogi.workouttimer.data.database.entities.Timer
import com.ivtogi.workouttimer.data.database.entities.TimerWithExercises

@Dao
interface TimerDao {

    @Query("SELECT * FROM Timers WHERE id = :id")
    suspend fun getTimerById(id: Int): Timer

    @Query("SELECT * FROM Timers WHERE id = :id")
    suspend fun getTimerWithExercisesById(id: Int): TimerWithExercises

    @Insert
    suspend fun insertTimer(timer: Timer)
}