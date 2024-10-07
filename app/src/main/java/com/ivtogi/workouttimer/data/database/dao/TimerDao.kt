package com.ivtogi.workouttimer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
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

    @Insert
    suspend fun insertTimer(timer: Timer): Int

    @Transaction
    @Insert
    suspend fun insertTimerWithExercises(timer: Timer, exercises: List<Exercise>){
        val timerId = insertTimer(timer)
        exercises.forEach { exercise -> insertExercise(exercise.copy(timerId = timerId)) }
    }

    @Query("SELECT * FROM exercises WHERE timerId = :timerId")
    suspend fun getExercisesByTimerId(timerId: Int): List<Exercise>

    @Insert
    suspend fun insertExercise(exercise: Exercise)
}