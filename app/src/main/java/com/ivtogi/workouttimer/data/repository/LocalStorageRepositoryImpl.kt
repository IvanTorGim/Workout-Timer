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

    override suspend fun saveTimer(timer: Timer): Long = try {
        timerDao.insertTimer(timer.toEntity())
    } catch (e: Exception) {
        0
    }

    override suspend fun getTimerById(id: Int): Timer = timerDao.getTimerById(id).toDomain()
}
