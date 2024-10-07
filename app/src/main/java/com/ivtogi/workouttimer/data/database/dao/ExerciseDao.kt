package com.ivtogi.workouttimer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ivtogi.workouttimer.data.database.entities.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises WHERE timerId = :timerId")
    suspend fun getExercisesByTimerId(timerId: Int): List<Exercise>

    @Insert
    suspend fun insertExercise(exercise: Exercise)
}