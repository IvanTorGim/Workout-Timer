package com.ivtogi.workouttimer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ivtogi.workouttimer.data.local.entities.ExerciseEntity
import com.ivtogi.workouttimer.data.local.entities.TimerEntity
import com.ivtogi.workouttimer.data.local.entities.TimerWithExercises

@Dao
interface TimerDao {

    @Query("SELECT * FROM Timers WHERE id = :id")
    suspend fun getTimerById(id: Int): TimerEntity

    @Transaction
    @Query("SELECT * FROM Timers WHERE id = :id")
    suspend fun getTimerWithExercisesById(id: Int): TimerWithExercises

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimer(timer: TimerEntity): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimerWithExercises(timer: TimerEntity, exercises: List<ExerciseEntity>) {
        val timerId = insertTimer(timer)
        exercises.forEach { exercise -> insertExercise(exercise.copy(timerId = timerId.toInt())) }
    }

    @Query("SELECT * FROM exercises WHERE timerId = :timerId")
    suspend fun getExercisesByTimerId(timerId: Int): List<ExerciseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity): Long
}