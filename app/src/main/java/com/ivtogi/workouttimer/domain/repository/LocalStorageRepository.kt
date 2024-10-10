package com.ivtogi.workouttimer.domain.repository

import com.ivtogi.workouttimer.domain.model.Timer

interface LocalStorageRepository {
    suspend fun saveTimer(timer: Timer): Boolean
    suspend fun getTimerById(id: String): Timer
}