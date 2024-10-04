package com.ivtogi.workouttimer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ivtogi.workouttimer.data.database.entities.TimerWithExercises

@Dao
interface TimerWithExercisesDao {

    @Query("SELECT * FROM Timer WHERE id = :id")
    suspend fun getTimerWithExercisesById(id: Int): TimerWithExercises

    @Insert
    suspend fun insertTimerWithExercise(timerWithExercises: TimerWithExercises)
}