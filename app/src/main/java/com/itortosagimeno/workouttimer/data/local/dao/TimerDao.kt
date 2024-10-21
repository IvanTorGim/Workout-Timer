package com.itortosagimeno.workouttimer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itortosagimeno.workouttimer.data.local.entities.TimerEntity

@Dao
interface TimerDao {

    @Query("SELECT * FROM Timers WHERE id = :id")
    suspend fun getTimerById(id: Int): TimerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimer(timer: TimerEntity): Long
}