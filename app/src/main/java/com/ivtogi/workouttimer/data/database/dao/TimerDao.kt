package com.ivtogi.workouttimer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ivtogi.workouttimer.data.database.entities.Exercise
import com.ivtogi.workouttimer.data.database.entities.Timer
import com.ivtogi.workouttimer.data.database.entities.TimerWithExercises

@Dao
interface TimerDao {

    @Query("SELECT * FROM Timers WHERE id = :id")
    suspend fun getTimerById(id: Int): Timer

    @Transaction
    @Query("SELECT * FROM Timers WHERE id = :id")
    suspend fun getTimerWithExercisesById(id: Int): TimerWithExercises

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimer(timer: Timer): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimerWithExercises(timer: Timer, exercises: List<Exercise>){
        val timerId = insertTimer(timer)
        exercises.forEach { exercise -> insertExercise(exercise.copy(timerId = timerId.toInt())) }
    }

    @Query("SELECT * FROM exercises WHERE timerId = :timerId")
    suspend fun getExercisesByTimerId(timerId: Int): List<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise): Long
}