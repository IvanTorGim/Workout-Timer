package com.ivtogi.workouttimer.data.repository

import com.ivtogi.workouttimer.data.local.dao.TimerDao
import com.ivtogi.workouttimer.data.mappers.toEntity
import com.ivtogi.workouttimer.domain.model.Timer
import com.ivtogi.workouttimer.domain.repository.LocalStorageRepository
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(
    private val timerDao: TimerDao
) : LocalStorageRepository {
    override suspend fun saveTimer(timer: Timer): Boolean {
        val timerEntity = timer.toEntity()
        val exercisesEntities = timer.workout.map { it.toEntity() }
        try {
            timerDao.insertTimerWithExercises(timerEntity, exercisesEntities)
            return true
        }catch (e: Exception){
            return false
        }
    }

    override suspend fun getTimerById(id: String): Timer {
        TODO("Not yet implemented")
    }

}
