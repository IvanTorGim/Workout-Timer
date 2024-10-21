package com.itortosagimeno.workouttimer.data.repository

import com.itortosagimeno.workouttimer.data.local.dao.TimerDao
import com.itortosagimeno.workouttimer.data.mappers.toDomain
import com.itortosagimeno.workouttimer.data.mappers.toEntity
import com.itortosagimeno.workouttimer.domain.model.Timer
import com.itortosagimeno.workouttimer.domain.repository.LocalStorageRepository
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
