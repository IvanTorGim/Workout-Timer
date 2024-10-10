package com.ivtogi.workouttimer.data.repository

import com.ivtogi.workouttimer.data.local.dao.TimerDao
import com.ivtogi.workouttimer.data.mappers.toDomain
import com.ivtogi.workouttimer.data.mappers.toEntity
import com.ivtogi.workouttimer.domain.model.Timer
import com.ivtogi.workouttimer.domain.repository.LocalStorageRepository
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(
    private val timerDao: TimerDao
) : LocalStorageRepository {
    override suspend fun saveTimerWithExercises(timer: Timer): Long {
        val timerEntity = timer.toEntity()
        val exercisesEntities = timer.workout.map { it.toEntity() }
        return try {
            timerDao.insertTimerWithExercises(timerEntity, exercisesEntities)
        } catch (e: Exception) {
            0
        }
    }

    override suspend fun getTimerWithExercisesById(id: Int): Timer {
        val result = timerDao.getTimerWithExercisesById(id)
        val exercises = result.exercises.map { it.toDomain() }
        val timer = result.timer.toDomain(exercises)
        return timer
    }

}
